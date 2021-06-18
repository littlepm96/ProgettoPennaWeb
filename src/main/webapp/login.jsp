<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title> Login Page </title>
    <style>
        Body {
            font-family: Calibri, Helvetica, sans-serif;
            background-color: #c0e8ff;
        }
        button {
            background-color: rgba(175, 76, 76, 0.67);
            width: 100%;
            color: rgb(4, 0, 0);
            padding: 15px;
            margin: 10px;
            border: none;
            cursor: pointer;
        }
        form {
            border: 3px solid #f1f1f1;
        }
        input[type=text], input[type=password] {
            width: 100%;
            margin: 8px 0;
            padding: 12px 20px;
            display: inline-block;
            border: 2px solid green;
            box-sizing: border-box;
        }
        button:hover {
            opacity: 0.7;
        }
        .cancelbtn {
            width: auto;
            padding: 10px 18px;
            margin: 10px 5px;
        }


        .container {
            padding: 25px;
            background-color: lightblue;
        }
    </style>
</head>
<body>
<div style="text-align: center;"> <h1> Login Test </h1> </div>
<form>
    <div class="container">
        <label>Username : </label>
        <label>
            <input type="text" placeholder="Enter Username" name="username" required>
        </label>
        <label>Password : </label>
        <label>
            <input type="password" placeholder="Enter Password" name="password" required>
        </label>
        <button type="submit"> <h3> Login </h3> </button>
        <label>
            <label>
                <button  type="submit"> <h3> Non possiedi un account? </h3> </button>
            </label>
            <input type="checkbox" checked="checked">
        </label> Remember me
        <button type="button" class="cancelbtn"> Cancel</button>
        Forgot <a href="#"> password? </a>
    </div>
</form>
</body>
</html>
