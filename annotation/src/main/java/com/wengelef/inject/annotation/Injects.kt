package com.wengelef.inject.annotation

import kotlin.reflect.KClass

@Target(AnnotationTarget.CLASS)
annotation class Injects(val component: KClass<*>)