/*
 * Copyright 2004-2005 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import org.springframework.core.io.Resource
import org.codehaus.groovy.grails.plugins.orm.hibernate.*
import org.codehaus.groovy.grails.commons.*

/**
 * Handles the configuration of Hibernate within Grails.
 *
 * @author Graeme Rocher
 * @since 0.4
 */
class HibernateGrailsPlugin {
    def author = "Graeme Rocher"
    def title = "Hibernate for Grails"
    def description = "Provides integration between Grails and Hibernate through GORM"

	def grailsVersion = "2.0 > *"
    def version = "2.0.0.M2"
    def documentation = "http://grails.org/doc/$version"
    def observe = ['domainClass']

    def pluginExcludes = [
        "grails-app/domain/org/grails/*.groovy"
    ]

    def dependsOn = [dataSource: "2.0 > *",
                     i18n: "2.0 > *",
                     core: "2.0 > *",
                     domainClass: "2.0 > *"]

    def loadAfter = ['controllers', 'domainClass']

    def watchedResources = ["file:./grails-app/conf/hibernate/**.xml"]

    def artefacts = [new AnnotationDomainClassArtefactHandler()]

    def doWithSpring = HibernatePluginSupport.doWithSpring

    def doWithDynamicMethods = HibernatePluginSupport.doWithDynamicMethods

    def onChange = HibernatePluginSupport.onChange
}
