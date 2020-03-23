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
        <h1 class="page-announce-text valign">Retour d'un livre</h1>
      </div>

      <c:if test="${! empty errorMessage}">
        <div>
          <p align="center"><c:out value="${errorMessage}"/></p>
        </div>
      </c:if>
      
      <div class="row">
      <div class="container">
        <h5>Selectionnez le livre a retourner</h5>
        <div class="row">
	      <form action="/Projet-Ric-David/emprunt_return" method="post" class="col s12">
	        <div class="row">
	          <div class="input-field col s12">
	            <select id="id" name="id" class="browser-default">
                <c:choose>
                  <c:when test="${(not empty id) && (! empty lendingListJSP)}">
                    <c:forEach var="loan" items="${lendingListJSP}">
                      <c:if test="${loan.getId() eq id}">
                        <option value="<c:out value="${loan.getId()}"/>" selected>"<c:out value="${loan.getBook().getTitle()}"/>", emprunte par <c:out value="${loan.getMember().getName()}"/> <c:out value="${loan.getMember().getLastname()}"/></option>
                      </c:if>
                    </c:forEach>
                  </c:when>
                  <c:otherwise>
                    <option value="" disabled selected>---livre emprunte par membre---</option>
                  </c:otherwise>
                </c:choose>
                <c:if test="${! empty lendingListJSP}">
                  <c:forEach var="loan" items="${lendingListJSP}">
                      <c:if test="${(empty id) || (loan.getId() ne id)}">
                        <option value="<c:out value="${loan.getId()}"/>">"<c:out value="${loan.getBook().getTitle()}"/>", emprunte par <c:out value="${loan.getMember().getName()}"/> <c:out value="${loan.getMember().getLastname()}"/></option>
                      </c:if>
                    </c:forEach>
                </c:if>
                  <!-- TODO : parcourir la liste des emprunts non rendus et afficher autant d'options que n�cessaire, sur la base de l'exemple ci-dessous -->
                  <!-- TODO : si l'attribut id existe, l'option correspondante devra �tre s�lectionn�e par d�faut (ajouter l'attribut selected dans la balise <option>) -->
              </select> 
	          </div>
	        </div>
	        <div class="row center">
	          <button class="btn waves-effect waves-light" type="submit">Retourner le livre</button>
	          <button class="btn waves-effect waves-light orange" type="reset">Annuler</button>
	        </div>
	      </form>
	    </div>
      </div>
      </div>
    </section>
  </main>
  <jsp:include page='footer.jsp'></jsp:include>
  <script>
    document.addEventListener('DOMContentLoaded', function() {
	  var elems = document.querySelectorAll('select');
	  var instances = M.FormSelect.init(elems, {});
	});
  </script>
</body>
</html>
