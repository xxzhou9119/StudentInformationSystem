Github Link: https://github.com/xxzhou9119/StudentInformationSystem

Elastic Beanstalk：http://studentinformationsystems-env.ye3ct3gm25.us-east-1.elasticbeanstalk.com/



Course
1. GET

  ...webapi/courses/

  ...webapi/courses/{courseid} (...webapi/courses/001)

2. POST

  ...webapi/courses/

  body:

{

"boradId": "007",

"courseId": "007",

"department": "info",

"professorId": "a.x",

"studentIds": [],

"studentTaId": "liu.a"

}



Student

POST

...webapi/students/

body:

{
        "courseIds": [],
        "department": "info",
        "emailId": YOUR TESTEMAIL,
        "firstName": "san",
        "lastName": "zhang",
        "studentId": "zhang.s"
    }

...webapi/students/zhang.s/register

body

 {
        "SNSTopicArn": "arn:aws:sns:us-east-1:599961460399:course004",
        "boradId": "004",
        "courseId": "004",
        "department": "info",
        "id": "457aa757-7236-4224-8dcc-003aaaa99349",
        "professorId": "a.x",
        "studentIds": [
            "z.x"
        ],
        "studentTaId": "liu.a"
    }



Announcement

1. GET

   ...webapi/announcements/

...webapi/announcements/{boardId}-{announcementId}  (...webapi/announcements/004-02)

2. POST

...webapi/announcements/

 body:

 {

    "announcementId": "06",

    "announcementText": "aaaaaaaaaaaa",

    "boardId": "004"

}



