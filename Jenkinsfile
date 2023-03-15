pipeline {
    agent any
    tools {
        jdk 'Java17'
    }
    environment {
        PROJECT_NAME = "mz2mo-server"

        DISCORD_WEBHOOK_URL = "https://discord.com/api/webhooks/1083501571083599873/4prm5Aoo_G84zQjvDhQ-i4lTWipLHArrTFVVx9UxXf7rk5QDCk2FWoXRHkh6uXk_jzCt"

        GITHUB_CREDENTIAL_ID = "github"
        GITHUB_BRANCH = "master"
        GITHUB_URL = "https://github.com/EmoHipHop/mz2mo-server"

        DOCKER_CREDENTIAL_ID = "docker"
        DOCKER_IMAGE_NAME = "mz2mo/mz2mo-server"
        DOCKER_CONTAINER_NAME = "mz2mo-server"
    }
    stages {
        stage('Github Checkout') {
            steps {
                git branch: GITHUB_BRANCH,
                    url: GITHUB_URL
            }
        }

        stage('Prepare Permission') {
            steps {
                sh 'chmod +x gradlew'
            }
        }

        stage('Gradle Test') {
            steps {
                sh  './gradlew clean test'
            }
        }

        stage('Gradle Build') {
            steps {
                sh './gradlew clean build -x test'
            }
        }

        stage('Docker Build') {
            steps {
                script {
                    app = docker.build(DOCKER_IMAGE_NAME)
                    docker.withRegistry('https://registry.hub.docker.com', DOCKER_CREDENTIAL_ID) {
                        app.push("${env.BUILD_NUMBER}")
                        app.push("latest")
                    }
                }
            }
        }

        stage('Docker Run') {
            steps {
                sh "docker ps -q --filter \"name=${DOCKER_CONTAINER_NAME}\" || grep -q . && docker stop ${DOCKER_CONTAINER_NAME} && docker rm ${DOCKER_CONTAINER_NAME} || true"
                sh "docker run -p 8081:8080 --name=${DOCKER_CONTAINER_NAME}  -d mz2mo/mz2mo-server"
            }
        }
    }

    post {
        always {
            sh "docker image prune -a -f"
        }
        success {
            discordSend description: "${env.BUILD_NUMBER}번째 어플리케이션 배포에 성공했어요!",
              footer: "젠킨스 배포 알림",
              link: env.BUILD_URL, result: currentBuild.currentResult,
              title: "${env.PROJECT_NAME}#${env.BUILD_NUMBER}",
              webhookURL: env.DISCORD_WEBHOOK_URL
        }
        failure {
            discordSend description: "${env.BUILD_NUMBER}번째 어플리케이션 배포에 실패했어요!",
              footer: "젠킨스 배포 알림",
              link: env.BUILD_URL, result: currentBuild.currentResult,
              title: "${env.PROJECT_NAME}#${env.BUILD_NUMBER}",
              webhookURL: env.DISCORD_WEBHOOK_URL
        }
    }
}