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
      <h5>Details du livre: <c:out value="${bookJSP.getId()}"/></h5>
        <div class="row">
	      <form action="/Projet-Ric-David/livre_details?id=<c:out value="${bookJSP.getId()}"/>" method="post" class="col s12"> <!-- TODO : remplacer idDuLivre par l'id du livre -->
	        <div class="row"> 
	          <div class="input-field col s12">
	            <input id="titre" type="text" value="<c:out value="${bookJSP.getTitle()}"/>" name="titre"> <!-- TODO : remplacer titreDuLivre par le titre du livre -->
	            <label for="titre">Titre</label>
	          </div>
	        </div>
	        <div class="row">
	          <div class="input-field col s6">
	            <input id="auteur" type="text" value="<c:out value="${bookJSP.getAuthor()}"/>" name="auteur"> <!-- TODO : remplacer auteurDuLivre par l'auteur du livre -->
	            <label for="auteur">Auteur</label>
	          </div>
	          <div class="input-field col s6">
	            <input id="isbn" type="text" value="<c:out value="${bookJSP.getIsbn()}"/>" name="isbn"> <!-- TODO : remplacer isbnDuLivre par l'isbn du livre -->
	            <label for="isbn">ISBN 13</label>
	          </div>
	        </div>
	        <div class="row center">
	          <button class="btn waves-effect waves-light" type="submit">Enregistrer</button>
	          <button class="btn waves-effect waves-light orange" type="reset">Annuler</button>
	        </div>
	      </form>
	      
	      <form action="/LibraryManager/livre_delete" method="get" class="col s12">
	        <input type="hidden" value="<c:out value="${bookJSP.getId()}"/>" name="id"> <!-- TODO : remplacer idDuLivre par l'id du livre -->
	        <div class="row center">
	          <button class="btn waves-effect waves-light red" type="submit">Supprimer le livre
	            <i class="material-icons right">delete</i>
	          </button>
	        </div>
	      </form>
	    </div>
        <div class="row">
          <div class="col s12">
	        <table class="striped">
              <thead>
                <tr>
                  <th>Emprunteur</th>
                  <th>Date d'emprunt</th>
                  <th>Retour</th>
                </tr>
              </thead>
              <tbody id="results">
                <c:if test="${! empty lendingListJSP}">
                    <c:forEach var="loan" items="${lendingListJSP}">
                      <tr>
                        <td><c:out value="${loan.getMember().getName()}" /> <c:out value="${loan.getMember().getLastname()}" /></td>
                        <td><c:out value="${loan.getLendDate()}" /></td>
                        <td><a href="emprunt_return?id=${loan.getId()}">edit<ion-icon class="table-item" name="log-in"></a></td>													
                      </tr>
                    </c:forEach>
                  </c:if>
				<!-- TODO : parcourir la liste des emprunts en cours pour ce livre et les afficher selon la structure d'exemple ci-dessus -->
              </tbody>
            </table>
          </div>
        </div>
      </div>
      </div>
    </section>
  </main>
  <jsp:include page='footer.jsp'></jsp:include>
</body>
</html>
