<html>
  <head>
    <title>Ktor Christmas List!</title>
  </head>
  <style>
      .container{
          display:flex;
          flex-wrap:wrap;
          text-align:center;
          justify-content: center;
      }

  </style>
  <body>
    <div class="container">
      <#-- @ftlvariable name="model" type="com.abnormallydriven.christmaslistservice.IndexPageModel" -->

      <div style="width: 100%;">
          <#if model.error??>
            <h1>Invalid User Data!</h1>
          </#if>

          <#if model.users?has_content>
            <h1>Christmas List Users</h1>
          </#if>

          <#list model.users as user>
            <p>${user.name} ${user.isNiceString()} <#if user.isNice()><a href="users/${user.id}/christmaslist">View WishList</a></#if></p>
          <#else>
            <h1>You should add some users</h1>
          </#list>
      </div>

      <form action="/" method="post" enctype="application/x-www-form-urlencoded" style="border: black 1px solid; padding: 10px;">
        <div>Name:</div>
        <div><input type="text" name="name"/></div>
        <div>Is Nice?:</div>
        <div>
          <input type="radio" name="isNice" value="true" checked>True<br>
          <input type="radio" name="isNice" value="false">False<br>
        </div>
        <div><input type="submit" value="Add"/></div>
      </form>

    </div>
  </body>
</html>