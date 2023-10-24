<%@ page language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8" ELIgnored="false" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title></title>
    </head>

    <body>
        <h2>Product Info update </h2>
        <hr>
        <form action="/student?action=update" method="POST">
            <input type="text" readonly value="${s.id}" name="id">
            <input type="text" readonly value="${s.name}" name="name">
            <input type="text" readonly value="${s.univ}" name="maker">
            <input type="text" value="${s.email}" name="email">
            <input type="text" readonly value="${s.birth}" name="date">
            <input type="submit" value="수정">
        </form>
    </body>
</html>