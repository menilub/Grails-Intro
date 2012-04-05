import grails.util.GrailsUtil
/**
 * Created by IntelliJ IDEA.
 * User: 541116
 * Date: 2009-12-10
 * Time: 21:34:31
 * To change this template use File | Settings | File Templates.
 */
class GrailsMelodyUtil {

    static ConfigObject getGrailsMelodyConfig() {

		GroovyClassLoader classLoader = new GroovyClassLoader(GrailsMelodyUtil.getClassLoader())

		def slurper = new ConfigSlurper(GrailsUtil.environment)
		ConfigObject userConfig
		try {
			userConfig = slurper.parse(classLoader.loadClass('GrailsMelodyConfig'))
		}
		catch (e) {
			// ignored, use defaults
		}

        return userConfig
	}
}
