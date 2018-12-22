<html>
<head>
  <title>Ktor Christmas List!</title>
</head>
<body style="padding: 20dp">
<#-- @ftlvariable name="model" type="com.abnormallydriven.christmaslistservice.IndexPageModel" -->

  <#if model.error??>
    <h1>Invalid User Data!</h1>
  </#if>

  <#list model.users as user>
    <p>someUser</p>
  <#else>
    <h1>You should add some users</h1>
  </#list>

<form action="/" method="post" enctype="application/x-www-form-urlencoded">
  <div>Name:</div>
  <div><input type="text" name="name"/></div>
  <div>Is Nice?:</div>
  <div>
    <input type="radio" name="isNice" value="true">True<br>
    <input type="radio" name="isNice" value="false">False<br>
  </div>
  <div><input type="submit" value="Login"/></div>
</form>


</body>
</html>