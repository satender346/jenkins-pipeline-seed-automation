//Sample seed file to create jenkins job (sample-pipeline) in deployment folder
// with paramters and environment variables.
JOB_FOLDER="deployment"
folder("${JOB_FOLDER}")
JOB_NAME = "cloud"
pipelineJob("${JOB_FOLDER}/${JOB_NAME}") {
  displayName("Deployment pipeline")
  description("Sample jenkins deployment pipeline")
  logRotator{
     daysToKeep(90)
  }
  parameters {
         string {
            defaultValue("dev1")
            description("Site name")
            name("SITE_NAME")
            trim(true)
         }
         string {
              defaultValue("master")
              description("Pegleg image")
              name("PEGLEG_IMAGE")
              trim(true)
         }
         booleanParam {
                defaultValue(true)
                description("Execute shipyard actions on genesis node using internal shipyard url.")
                name("EXECUTE_TEST_CASE")
        }
       environmentVariables(
          "FILE_PATH": "/var/jenkins_home/sample_file"
       )
  }
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