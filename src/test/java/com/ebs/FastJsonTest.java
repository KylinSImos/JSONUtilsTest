package com.ebs;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.ebs.domain.Course;
import com.ebs.domain.Student;
import com.ebs.domain.Teacher;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class FastJsonTest {

    //json字符串-简单对象型
    private static final String JSON_OBJ_STR = "{\"stuName\":\"lily\",\"stuAge\":12}";

    //json字符串-数组类型
    private static final String JSON_ARRAY_STR = "[{\"stuName\":\"lily\",\"stuAge\":12},{\"stuName\":\"lucy\",\"stuAge\":15}]";

    //复杂格式json字符串
    private static final String COMPLEX_JSON_STR = "{\"teaName\":\"crystall\",\"teaAge\":27,\"teaCours\":{\"courName\":\"english\",\"courCode\":1270},\"teaStus\":[{\"stuName\":\"lily\",\"stuAge\":12},{\"stuName\":\"lucy\",\"stuAge\":15}]}";


    /**
     * json字符串-简单对象型到JSONObject的转换
     */
    @Test
    public void testJSONStrToJSONObject() {
        JSONObject jsonObject = JSONObject.parseObject(JSON_OBJ_STR);
        System.out.println("studentName:" + jsonObject.getString("stuName") + " -- " + "studentAge:" + jsonObject.getInteger("stuAge"));

    }

    /**
     * JSONObject 到 json 字符串-简单对象型转换
     */
    @Test
    public void testJSONObjectToJSONStr() {
        JSONObject jsonObject = JSONObject.parseObject(JSON_OBJ_STR);
        //第一种方式
        String jsonString1 = jsonObject.toJSONString(jsonObject);
        System.out.println(jsonString1);
        //第二种方式
        String jsonString2 = jsonObject.toJSONString();
        System.out.println(jsonString2);
    }

    /**
     * json 字符串-数组类型到 JSONArray 的转换
     */
    @Test
    public void testJSONStrToJSONArray() {
        JSONArray jsonArray = JSONArray.parseArray(JSON_ARRAY_STR);

        //遍历方式1
        int size = jsonArray.size();
        for (int i = 0; i < size; i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            System.out.println("studentName:" + jsonObject.getString("stuName") + ":" + "studentAge:" + jsonObject.getInteger("stuAge"));
        }

        //遍历方式2
        for (Object obj : jsonArray
                ) {
            JSONObject jsonObject = (JSONObject) obj;
            System.out.println("studentName:" + jsonObject.getString("stuName") + ":" + "studentAge:" + jsonObject.getInteger("stuAge"));
        }

    }

    /**
     * JSONArray 到 json 字符串-数组类型的转换
     */
    @Test
    public void testJSONArrayToJSONStr() {

        //已知 JSONArray，目标要转换为 json 字符串
        JSONArray jsonArray = JSONArray.parseArray(JSON_ARRAY_STR);
        //第一种方式
        String jsonString1 = JSONArray.toJSONString(jsonArray);
        System.out.println(jsonString1);

        //第二种方式
        String jsonString2 = jsonArray.toJSONString(jsonArray);
        System.out.println(jsonString2);

    }

    /**
     * 复杂 json 格式字符串到 JSONObject 的转换
     */
    @Test
    public void testComplexJSONStrToJSONObject() {
        JSONObject jsonObject = JSONObject.parseObject(COMPLEX_JSON_STR);
        String teaName = jsonObject.getString("teaName");
        Integer teaAge = jsonObject.getInteger("teaAge");
        System.out.println("teacherName:" + teaName + " teacherAge:" + teaAge);
        JSONObject course = jsonObject.getJSONObject("course");
        String courName = course.getString("courName");
        Integer courCode = course.getInteger("courCode");
        System.out.println("courseName:" + courName + " courseCode:" + courCode);
        JSONArray teaStus = jsonObject.getJSONArray("teaStus");
        for (Object obj : teaStus
                ) {
            JSONObject jsonObjectStu = (JSONObject) obj;
            String stuName = jsonObjectStu.getString("stuName");
            Integer stuAge = jsonObjectStu.getInteger("stuAge");
            System.out.println("studentName:" + stuName + " studentAge:" + stuAge);
        }
    }


    /**
     * 复杂 JSONObject 到 json 格式字符串的转换
     */
    @Test
    public void testJSONObjectToComplexJSONStr() {

        //复杂 JSONObject，目标转换为 json 字符串
        JSONObject jsonObject = JSONObject.parseObject(COMPLEX_JSON_STR);
        //第一种方式
        String jsonString1 = JSONObject.toJSONString(jsonObject);
        System.out.println(jsonString1);
        //第二种方式
        String jsonString2 = jsonObject.toJSONString();
        System.out.println(jsonString2);
    }

    /**
     * json 字符串-简单对象到 JavaBean 之间的转换
     */
    @Test
    public void testJSONStrToJavaBeanObj() {

        //第一种方式
        JSONObject jsonObject = JSONObject.parseObject(JSON_OBJ_STR);
        String stuName = jsonObject.getString("stuName");
        Integer stuAge = jsonObject.getInteger("stuAge");
        Student student1 = new Student(stuName, stuAge);
        System.out.println(student1);

        //第二种方式，使用 TypeReference<T> 类，由于其构造方法使用 protected 进行修饰，故创建其子类
        Student student2 = JSONObject.parseObject(JSON_OBJ_STR, new TypeReference<Student>() {
        });
        System.out.println(student2);

        //第三种方式，使用 Gson 的思想
        Student student3 = JSONObject.parseObject(JSON_OBJ_STR, Student.class);
        System.out.println(student3);
    }


    /**
     * JavaBean 到 json 字符串-简单对象的转换
     */
    @Test
    public void testJavaBeanObjectToJSONStr() {

        Student student = new Student("simon", 25);
        String jsonString = JSONObject.toJSONString(student);
        System.out.println(jsonString);

    }


    /**
     * json 字符串-数组类型到 JavaBean_List 的转换
     */
    @Test
    public void testJSONStrToJavaBeanList() {

        //第一种方式
        JSONArray jsonArray = JSONArray.parseArray(JSON_ARRAY_STR);

        List<Student> students1 = new ArrayList<>();
        //遍历 JSONArray
        for (Object object : jsonArray
                ) {
            JSONObject jsonObject = (JSONObject) object;
            String stuName = jsonObject.getString("stuName");
            Integer stuAge = jsonObject.getInteger("stuAge");
            students1.add(new Student(stuName, stuAge));

        }
        System.out.println(students1);

        //第二种方式，使用 TypeReference<T> 类，由于其构造方法使用 protected 进行修饰，故创建其子类
        List<Student> studentList2 = JSONArray.parseObject(JSON_ARRAY_STR, new TypeReference<ArrayList<Student>>() {
        });
        System.out.println(studentList2);


        //第三种方式，使用 Gson 的思想
        List<Student> studentList3 = JSONArray.parseArray(JSON_ARRAY_STR, Student.class);
        System.out.println(studentList3);
    }

    /**
     * JavaBean_List 到 json 字符串-数组类型的转换
     */
    @Test
    public void testJavaBeanListToJSONStr() {
        Student student1 = new Student("simon", 25);
        Student student2 = new Student("sylier", 25);
        List<Student> list = new ArrayList<>();
        list.add(student1);
        list.add(student2);
        String jsonString = JSONArray.toJSONString(list);
        System.out.println(jsonString);

    }

    /**
     * 复杂 json 格式字符串到 JavaBean_obj 的转换
     */
    @Test
    public void testComplexJSONStrToJavaBean() {

        //第一种方式
        JSONObject jsonObject = JSONObject.parseObject(COMPLEX_JSON_STR);
        String teaName = jsonObject.getString("teaName");
        Integer teaAge = jsonObject.getInteger("teaAge");
        JSONObject teaCours = jsonObject.getJSONObject("teaCours");
        String courName = teaCours.getString("courName");
        Integer courCode = teaCours.getInteger("courCode");
        JSONArray students = jsonObject.getJSONArray("teaStus");
        List<Student> studentList = new ArrayList<>();
        for (Object object : students
                ) {
            JSONObject jsonObject1 = (JSONObject) object;
            String stuName = jsonObject1.getString("stuName");
            Integer stuAge = jsonObject1.getInteger("stuAge");
            Student student = new Student(stuName, stuAge);
            studentList.add(student);
        }
        Teacher teacher = new Teacher();
        teacher.setTeaName(teaName);
        teacher.setTeaAge(teaAge);
        teacher.setTeaCours(new Course(courName, courCode));
        teacher.setTeaStus(studentList);
        System.out.println(teacher);

        //第二种方式，使用 TypeReference<T> 类，由于其构造方法使用 protected 进行修饰，故创建其子类
        Teacher teacher2 = JSONObject.parseObject(COMPLEX_JSON_STR, new TypeReference<Teacher>() {
        });
        System.out.println(teacher2);

        //第三种方式，使用 Gson 思想
        Teacher teacher3 = JSONObject.parseObject(COMPLEX_JSON_STR, Teacher.class);
        System.out.println(teacher3);
    }

    /**
     * 复杂 JavaBean_obj 到 json 格式字符串的转换
     */
    @Test
    public void testJavaBeanToComplexJSONStr() {

        //已知复杂 JavaBean_obj
        //第一种方式
        Teacher teacher = JSONObject.parseObject(COMPLEX_JSON_STR, new TypeReference<Teacher>() {
        });
        String jsonString1 = JSONObject.toJSONString(teacher);
        System.out.println(jsonString1);


    }

    /**
     * 简单JavaBean_obj 到 json 对象的转换
     */
    @Test
    public void testJavaBeanToJSONObject() {

        //已知简单的 JavaBean_obj
        Student student = new Student("simon", 25);
        //方式一
        String jsonString = JSONObject.toJSONString(student);
        JSONObject jsonObject = JSONObject.parseObject(jsonString);
        System.out.println(jsonObject);
        //方式二
        JSONObject jsonObject1 = (JSONObject) JSONObject.toJSON(student);
        System.out.println(jsonObject1);
    }

    /**
     * 简单 json 对象到 JavaBean_obj 的转换
     */
    @Test
    public void testJSONObjectToJavaBean() {

        //已知简单 json 对象
        JSONObject jsonObject = JSONObject.parseObject(JSON_OBJ_STR);

        //第一种方式，使用 TypeReference<T> 类，由于其构造方法使用 protected 进行修饰，故创建其子类
        Student student = JSONObject.parseObject(jsonObject.toJSONString(), new TypeReference<Student>() {
        });
        System.out.println(student);

        //第二种方式，使用 Gson 思想
        Student student1 = JSONObject.parseObject(jsonObject.toJSONString(), Student.class);
        System.out.println(student1);

    }

    /**
     * JavaList 到 JsonArray 的转换
     */
    @Test
    public void testJavaListToJsonArray() {

        //已知 JavaList
        Student student = new Student("simon", 25);
        Student student1 = new Student("silyver", 25);
        List<Student> studentList = new ArrayList<>();
        studentList.add(student);
        studentList.add(student1);

        //方式一
        String jsonString = JSONArray.toJSONString(studentList);
        JSONArray jsonArray = JSONArray.parseArray(jsonString);
        System.out.println(jsonArray);

        //方式二
        JSONArray jsonArray1 = (JSONArray) JSONArray.toJSON(studentList);
        System.out.println(jsonArray1);
    }

    /**
     * JsonArray 到 JavaList 的转换
     */
    @Test
    public void testJsonArrayToJavaList() {

        //已知 JsonArray
        JSONArray jsonArray = JSONArray.parseArray(JSON_ARRAY_STR);

        //第一种方式,使用TypeReference<T>类,由于其构造方法使用protected进行修饰,故创建其子类
        List<Student> studentList = JSONArray.parseObject(jsonArray.toJSONString(), new TypeReference<List<Student>>() {
        });
        System.out.println(studentList);

        //第二种方式,使用Gson的思想
        List<Student> studentList1 = JSONArray.parseArray(jsonArray.toJSONString(), Student.class);
        System.out.println(studentList1);
    }

    /**
     * 复杂 JavaBean_obj 到 json 对象的转换
     */
    @Test
    public void testComplexJavaBeanTOJSONObject() {

        //已知复杂的 JavaBean_obj
        Student student = new Student("simon", 25);
        Student student1 = new Student("tom", 25);
        List<Student> studentList = new ArrayList<>();
        studentList.add(student);
        studentList.add(student1);

        Course course = new Course("math", 3009);
        Teacher teacher = new Teacher("Lily", 25, studentList, course);


        //方式一
        String jsonString = JSONObject.toJSONString(teacher);
        JSONObject jsonObject = JSONObject.parseObject(jsonString);
        System.out.println(jsonObject);

        //方式二
        JSONObject jsonObject1 = (JSONObject) JSONObject.toJSON(teacher);
        System.out.println(jsonObject1);
    }

    /**
     * 复杂 json 对象到 JavaBean_obj 的转换
     */
    @Test
    public void testComplexJSONObjectToJavaBean() {

        //已知复杂的 json 对象
        JSONObject jsonObject = JSONObject.parseObject(COMPLEX_JSON_STR);

        //第一种方式,使用TypeReference<T>类,由于其构造方法使用protected进行修饰,故创建其子类
        Teacher teacher = JSONObject.parseObject(jsonObject.toJSONString(), new TypeReference<Teacher>() {
        });
        System.out.println(teacher);

        //第二种方式,使用Gson的思想
        Teacher teacher1 = JSONObject.parseObject(jsonObject.toJSONString(), Teacher.class);
        System.out.println(teacher1);
    }


}
