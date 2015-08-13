import java.time.LocalDateTime

def localNow = LocalDateTime.now()
def localFuture = localNow.plusMinutes(2)

job("user-registration-start") {
    description("Automatically generated jenkins job, to not modify because your changes will be overriden!")
    concurrentBuild(false)
    logRotator(5, -1)
    deliveryPipelineConfiguration('build','build customer cache project')
    scm {
        git {
            remote {
                url(gitUrl)
            }
        }
    }
    triggers {
        scm('0,10,20,30,40,50 * * * *')
    }
    steps {

        maven {
            goals('clean install -T 4')
        }

    }

    publishers {
        publishCloneWorkspace('*/**')
        archiveJunit('**/target/surefire-reports/*.xml')
    }
}