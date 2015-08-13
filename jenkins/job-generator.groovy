import java.time.*

def gitUrl = 'https://github.com/SimonPNorra/user-registration.git' 

// Job that read the pipeline directory for a certain branch
job("user-registration-pipeline") {
    description("Branch Jenkins Job generator job")
    quietPeriod(60)
    parameters {
        stringParam('gitUrl', "${gitUrl}", "Git URL used for branch");
    }
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
        dsl(['pipeline/**/*.groovy'], 'DELETE')
    }
}