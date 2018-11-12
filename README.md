Github Link: https://github.com/xxzhou9119/StudentInformationSystem

Elastic Beanstalk: http://studentinformationsystems-env.ye3ct3gm25.us-east-1.elasticbeanstalk.com/

<Professor>
1. GET
webapi/professors/
webapi/professors/{professorid}  (webapi/professors/cc.J )

2. POST
webapi/professors/
body:

{
"department": "engineer",
"firstName": "Ann",
"lastName": "zhou"
}

3.PUT
webapi/professors/{professorid}  (webapi/professors/zhou.A )
body:
{
"department": "engineer",
"firstName": "Ann",
"id": "e4ee8a36-8a7a-4754-ad4b-398069b631fb",
"lastName": "zhang",
"professorId": "zhou.A"
}

4.DELETE
webapi/professors/{professorid}  (webapi/professors/zhou.A )

<Course>
1. GET
webapi/courses/
webapi/courses/{courseid} (webapi/courses/001)

2. POST
webapi/courses/
body:
{
"boradId": "03",
"courseId": "003",
"department": "info",
"professorId": "a.x",
"studentIds": [
"001",
"002",
"003"
],
"studentTaId": "liu.a"
}

3.PUT
webapi/courses/{courseid}  (webapi/courses/003)
body:
{
"boradId": "03",
"department": "info",
"professorId": "a.x",
"studentIds": [
"001",
"002",
"003"
],
"studentTaId": "zhou.a"
}
4.DELETE
webapi/courses/{courseid}  (webapi/courses/003)

