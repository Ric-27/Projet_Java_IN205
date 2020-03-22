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
      <div class="row">
      <div class="container">
        <h5>Selectionnez le livre a retourner</h5>
        <div class="row">
	      <form action="/LibraryManager/emprunt_return" method="post" class="col s12">
	        <div class="row">
	          <div class="input-field col s12">
	            <select id="id" name="id" class="browser-default">
                <c:if test="${id != null && !loanListJSP.isEmpty()}">

                </c:if>
	              <option value="" disabled selected>---</option>
                  <!-- TODO : parcourir la liste des emprunts non rendus et afficher autant d'options que n�cessaire, sur la base de l'exemple ci-dessous -->
                  <!-- TODO : si l'attribut id existe, l'option correspondante devra �tre s�lectionn�e par d�faut (ajouter l'attribut selected dans la balise <option>) -->
                  <option value="idDeLEmprunt">"Titre du livre", emprunt� par Pr�nom et nom du membre emprunteur</option>
                  <c:if test="${! empty loanListJSP}">
                    <c:forEach var="loan" items="${loanListJSP}">
                      <option value="<c:out value="${member.getId()}"/>"><c:out value="${member.getLastname()}"/>, <c:out value="${member.getName()}"/></option>
                    </c:forEach>
                  </c:if>
              </select>
              <select id="id" name="id" class="browser-default">
	              <% if (request.getAttribute("id") != null && !loanList.isEmpty()) {
                  for (Loan loan : loanList) {
                    if (loan.getId() == (int) request.getAttribute("id")) { %>
                      <option value='<%= loan.getId() %>' selected>"<%= loan.getBook().getTitle() %>", emprunté par <%= loan.getMember().getFirstName() %> <%= loan.getMember().getLastName() %></option>
                    <% }
                  }
                } else { %>
                  <option value="" default disabled selected>---</option>
                <% }
                if (!loanList.isEmpty()) {
                  for (Loan loan : loanList) {
                    if (request.getAttribute("id") == null || loan.getId() != (int) request.getAttribute("id")) { %>
                      <option value='<%= loan.getId() %>'>"<%= loan.getBook().getTitle() %>", emprunté par <%= loan.getMember().getFirstName() %> <%= loan.getMember().getLastName() %></option>
                    <% }
                  }
                } %>
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
