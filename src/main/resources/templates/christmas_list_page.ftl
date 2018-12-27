<#-- @ftlvariable name="model" type="com.abnormallydriven.christmaslistservice.ChristmasListPageModel" -->
<html>
  <head>
    <title>${model.user.name}'s Christmas List!</title>
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

      <div style="width: 100%;">

          <#if model.christmasList.getItems()?has_content>
            <h1>All ${model.user.name} wants for Christmas is...</h1>
          </#if>

          <#list model.christmasList.getItems() as item>
            <p>${item.title}</p>
          <#else>
            <h1>You should add some items</h1>
          </#list>
      </div>

      <form action="/users/${model.user.id}/christmaslist" method="post" enctype="application/x-www-form-urlencoded" style="border: black 1px solid; padding: 10px;">
        <div>Wishlist Item:</div>
        <div><input type="text" name="title"/></div>
        <div><input type="submit" value="Add"/></div>
      </form>

    </div>
  </body>
</html>