<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
</head>
<body>



<#if data??>
    <h1>Welcome ${data}</h1>
<#else>
    <h1>Welcome</h1>
    <p>Log in with the button to see the content.</p>
    <p>Use "admin" as username and password.</p>
</#if>

<a href="/admin/dashboard">Go to admin dashboard</a>
<#--    <form action="/login" method="post">-->
<#--        <button>Log in</button>-->
<#--    </form>-->
</body>
</html>
