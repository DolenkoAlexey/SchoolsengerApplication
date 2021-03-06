package com.example.alex.schoolsengerapplication.parsers;

import com.example.alex.schoolsengerapplication.json.usersJson.SchoolkidJson;
import com.example.alex.schoolsengerapplication.json.usersJson.SuperadminJson;
import com.example.alex.schoolsengerapplication.json.usersJson.TeacherJson;
import com.example.alex.schoolsengerapplication.models.users.Schoolkid;
import com.example.alex.schoolsengerapplication.models.users.Superadmin;
import com.example.alex.schoolsengerapplication.models.users.Teacher;

/**
 * Created by Alex on 12.05.2016.
 */
public class UserJsonParser {

    public Schoolkid ParseUserFromJson(SchoolkidJson schoolkidJson){

        Schoolkid schoolkid = new Schoolkid(schoolkidJson.getId(), schoolkidJson.getEmail(), schoolkidJson.getUsername(),
                schoolkidJson.getPassword(), schoolkidJson.getFirstname(), schoolkidJson.getLastname(),
                schoolkidJson.getClassNumber());
        return schoolkid;
    }

    public Teacher ParseUserFromJson(TeacherJson teacherJson){

        Teacher teacher = new Teacher(teacherJson.getId(), teacherJson.getEmail(), teacherJson.getUsername(),
                teacherJson.getPassword(), teacherJson.getFirstname(), teacherJson.getLastname());
        return teacher;
    }

    public Superadmin ParseUserFromJson(SuperadminJson superadminJson){

        Superadmin superadmin = new Superadmin(superadminJson.getId(), superadminJson.getEmail(), superadminJson.getUsername(),
                superadminJson.getPassword(), superadminJson.getFirstname(), superadminJson.getLastname());
        return superadmin;
    }

    public SchoolkidJson ParseUserToJson(Schoolkid schoolkid){

        SchoolkidJson schoolkidJson = new SchoolkidJson(schoolkid.getId(), schoolkid.getEmail(), schoolkid.getUsername(),
                schoolkid.getPassword(), schoolkid.getFirstname(), schoolkid.getLastname(),
                schoolkid.getClassNumber());
        return schoolkidJson;
    }

    public TeacherJson ParseUserToJson(Teacher teacher){

        TeacherJson teacherJson = new TeacherJson(teacher.getId(), teacher.getEmail(), teacher.getUsername(),
                teacher.getPassword(), teacher.getFirstname(), teacher.getLastname());
        return teacherJson;
    }
}
