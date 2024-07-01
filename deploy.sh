./gradlew clean build
ssh -i ~/todolist.pem ubuntu@ec2-43-203-120-111.ap-northeast-2.compute.amazonaws.com 'rm -rf ~/to-do-list-spring/to-do-list-0.0.1-SNAPSHOT.jar'
scp -i /Users/hyeontaelim/todolist.pem /Users/hyeontaelim/development/ToDoList/Java/to-do-list-Spring/build/libs/to-do-list-0.0.1-SNAPSHOT.jar ubuntu@ec2-43-203-230-110.ap-northeast-2.compute.amazonaws.com:/home/ubuntu/to-do-list-spring
ssh -i ~/todolist.pem ubuntu@ec2-43-203-120-111.ap-northeast-2.compute.amazonaws.com '~/deploy.sh'
