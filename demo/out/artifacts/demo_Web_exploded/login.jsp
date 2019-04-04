<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>登录</title>
</head>

<style>
    .login-container {
        margin: 50px;
        width: 100%;
    }

    .form-container {
        margin: 0px auto;
        width: 30%;
        text-align: center;
        box-shadow: 1px 1px 10px #888888;
        height: 300px;
        padding: 5px;
    }

    input {
        margin-top: 10px;
        width: 350px;
        height: 30px;
        border-radius: 3px;
        border: 1px #E9686B solid;
        padding-left: 2px;

    }


    .btn {
        width: 350px;
        height: 35px;
        line-height: 35px;
        cursor: pointer;
        margin-top: 20px;
        border-radius: 3px;
        background-color: #E9686B;
        color: white;
        border: none;
        font-size: 15px;
    }

    .title{
        margin-top: 5px;
        font-size: 18px;
        color: #E9686B;
    }
</style>
<body>
<div class="login-container">
    <div class="form-container">
        <p class="title">用户登录</p>
		<form action="http://localhost:8080/login" th:action="@{/login}" th:object="${UserMessage}" method="post">
			<input type="text" name="username" style="width: 80%;"  th:field="*{username}"  placeholder="用户名"/><br/>
			<input type="text" name="password" style="width: 80%;"  th:field="*{password}"  placeholder="密码"/>
            <button type="submit" style="width: 60%;" class="btn">登    录</button>
    </form>
    </div>
</div>
</body>
</html>
