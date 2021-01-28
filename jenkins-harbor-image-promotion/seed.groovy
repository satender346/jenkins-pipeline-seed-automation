//Sample seed file to create image promotion to harbor registry

JOB_FOLDER="image"
folder("${JOB_FOLDER}")
JOB_NAME = "harbor-promotion"
pipelineJob("${JOB_FOLDER}/${JOB_NAME}") {
  displayName("Harbor image promotion")
  description("Sample jenkins pipeline to promot image in harbor registry")
  logRotator{
     daysToKeep(90)
  }
  parameters {
         string {
            defaultValue("hello-world")
            description("docker image example ")
            name("IMAGE_NAME")
            trim(true)
         }
         string {
              defaultValue("admin")
              description("Harbor user name")
              name("HARBOR_USER_NAME")
              trim(true)
         }
         password {
              defaultValue("Harbor12345")
              description("Harbor password")
              name("HARBOR_USER_PASSWORD")
        }
  }
  concurrentBuild(false)
  triggers {
     definition {
       cps {
            def jenkinsfile = readFileFromWorkspace("jenkins-harbor-image-promotion/Jenkinsfile")
            script(jenkinsfile)
            sandbox(true)
          }
        }
     }
}