<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Library Management</title>
  <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
  <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.98.0/css/materialize.min.css">
  <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
  <link href="assets/css/custom.css" rel="stylesheet" type="text/css" />
</head>

<body>
  <jsp:include page='menu.jsp'></jsp:include>
  <main>
    <section class="content">
      <div class="page-announce valign-wrapper">
        <a href="#" data-activates="slide-out" class="button-collapse valign hide-on-large-only"><i class="material-icons">menu</i></a>
        <h1 class="page-announce-text valign">Fiche livre</h1>
      </div>

      <c:if test="${! empty errorMessage}">
        <div>
          <p align="center"><c:out value="${errorMessage}"/></p>
        </div>
      </c:if>

      <div class="row">
        <div class="container">
        <h5>Suppression du livre avec l'id: <c:out value="${bookJSP.getId()}"/></h5> 
          <div class="row">
            <p>etes-vous sur de vouloir supprimer le livre <c:out value="${bookJSP.getTitle()}"/> de <c:out value="${bookJSP.getAuthor()}"/> (<c:out value="${bookJSP.getIsbn()}"/>) ?</p> 
          <form action="livre_delete" method="post" class="col s12">
              <input type="hidden" value="<c:out value="${bookJSP.getId()}"/>" name="id">
            <div class="row center">
              <button class="btn waves-effect waves-light red" type="submit">Supprimer
                <i class="material-icons right">delete</i>
              </button>
              <a class="btn waves-effect waves-light orange" href="livre_details?id=<c:out value="${bookJSP.getId()}"/>">Annuler</a> 
            </div>
          </form>
        </div>  
        </div>
        </div>
      </section>
    </main>
    <jsp:include page='footer.jsp'></jsp:include>
  </body>
  </html>