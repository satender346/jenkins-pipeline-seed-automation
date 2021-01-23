//Sample seed file to create jenkins job (sample-pipeline) in deployment folder
// with paramters and environment variables.
folder("deployment")
JOB_NAME = "sample-pipeline"
pipelineJob("${JOB_NAME}") {
  displayName("Sample Jenkins deployment pipeline")
  description("Sample jenkins deployment pipeline")
  logRotator{
     daysToKeep(90)
  }
  stringParam('SITE_NAME', 'dev1', 'Site name')
  stringParam('GIT_BRANCH', 'main', 'GIT Branch name')
  booleanParam('EXECUTE_TEST_CASE', true, 'Run test case')
  
  environmentVariables(
    "FILE_PATH": "/var/jenkins_home/sample_file",
  )
  concurrentBuild(false)
  triggers {
     definition {
       cps {
            def jenkinsfile = readFileFromWorkspace("jenkins-seed-example/Jenkinsfile")
            script(jenkinsfile)
            sandbox(true)
          }
        }
     }
  }


