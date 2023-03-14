pipeline {
    agent any
    tools {
        jdk 'Java17'
    }
    environment {
        GITHUB_CREDENTIAL_ID = "github"
        GITHUB_BRANCH = "master"
        GITHUB_URL = "https://github.com/EmoHipHop/mz2mo-server"
        DOCKER_CREDENTIAL_ID = "docker"
        DOCKER_IMAGE_NAME = "mz2mo/mz2mo-server"
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
                sh 'docker run -p 8081:8080 -d mz2mo/mz2mo-server'
            }
        }
    }
}