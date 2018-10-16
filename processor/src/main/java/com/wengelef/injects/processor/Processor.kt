package com.wengelef.injects.processor

import com.google.auto.service.AutoService
import com.squareup.kotlinpoet.*
import com.wengelef.inject.annotation.Injects
import java.io.File
import javax.annotation.processing.AbstractProcessor
import javax.annotation.processing.Processor
import javax.annotation.processing.RoundEnvironment
import javax.lang.model.SourceVersion
import javax.lang.model.element.Element
import javax.lang.model.element.TypeElement
import javax.lang.model.type.MirroredTypeException
import javax.lang.model.type.TypeMirror
import kotlin.reflect.KClass

@AutoService(Processor::class)
class Processor : AbstractProcessor() {

    override fun getSupportedAnnotationTypes(): MutableSet<String> {
        return mutableSetOf(Injects::class.java.name)
    }

    override fun getSupportedSourceVersion(): SourceVersion {
        return SourceVersion.latest()
    }

    override fun process(set: MutableSet<out TypeElement>, environment: RoundEnvironment): Boolean {
        log("Processing...")

        val map: Map<TypeMirror, List<Element>> = environment.getElementsAnnotatedWith(Injects::class.java).groupBy { element ->
            val annotation: Injects = element.getAnnotation(Injects::class.java)

            var clazzType: TypeMirror? = null
            try {
                annotation.component
            } catch (e: MirroredTypeException) {
                clazzType = e.typeMirror
            }
            clazzType!!
        }

        map.forEach { (component, elements) ->

            log("Processing Component : ${component.asTypeName()}")

            val nameComponents = component.asTypeName().toString().split(".")
            val componentName = nameComponents.last()
            val packageName = nameComponents.minus(componentName).joinToString(".")


            val interfaceName = "${componentName}Injectors"
            val injectorBuilder = TypeSpec.interfaceBuilder(interfaceName)

            elements.forEach { element ->
                injectorBuilder.addFunction(
                    FunSpec.builder("inject")
                        .addModifiers(KModifier.ABSTRACT)
                        .addParameter("injector", element.asType().asTypeName())
                        .build()
                )
            }

            val file = FileSpec.builder(packageName, interfaceName)
                .addType(injectorBuilder.build())
                .build()

            file.writeTo(File(processingEnv.options["kapt.kotlin.generated"], "$interfaceName.kt"))
        }

        return true
    }

    private fun log(message: String) {
        println(message)
    }
}