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
        <h1 class="page-announce-text valign">Fiche membre</h1>
	  </div>
	  
	  <c:if test="${! empty errorMessage}">
        <div>
          <p align="center"><c:out value="${errorMessage}"/></p>
        </div>
	  </c:if>
	  
      <div class="row">
      <div class="container">
      <h5>Details du membre: <c:out value="${memberJSP.getId()}"/></h5> <!-- TODO : remplacer 007 par l'id du membre -->
        <div class="row">
	      <form action="/Projet-Ric-David/membre_details?id=<c:out value="${memberJSP.getId()}"/>" method="post" class="col s12"> <!-- TODO : remplacer idDuMembre par l'id du membre -->
	        <div class="row">
	          <div class="input-field col s4">
	            <input id="nom" type="text" value="<c:out value="${memberJSP.getLastname()}"/>" name="nom"> <!-- TODO : remplacer nomDuMembre par le nom du membre -->
	            <label for="nom">Nom</label>
	          </div>
	          <div class="input-field col s4">
	            <input id="prenom" type="text" value="<c:out value="${memberJSP.getName()}"/>" name="prenom"> <!-- TODO : remplacer prenomDuMembre par le pr�nom du membre -->
	            <label for="prenom">Prenom</label>
	          </div>
	          <div class="input-field col s4">
	            <select name="abonnement" class="browser-default">
	              <!-- TODO : faire en sorte que l'option correspondant � l'abonnement du membre soit s�lectionn�e par d�faut -->
	              <!-- Pour cela, vous devez rajouter l'attribut selecter sur la balise <option> concern�e -->
					<c:choose>
						<c:when test="${(memberJSP.getSubscription().toString() eq 'BASIC')}">
							<option value="BASIC" selected>-- BASIC --</option>1
						</c:when>
						<c:otherwise>
							<option value="BASIC">BASIC</option>
						</c:otherwise>
					</c:choose>
					<c:choose>
						<c:when test="${(memberJSP.getSubscription().toString() eq 'PREMIUM')}">
							<option value="PREMIUM" selected>-- PREMIUM --</option>
						</c:when>
						<c:otherwise>
							<option value="PREMIUM">PREMIUM</option>
						</c:otherwise>
					</c:choose>
					<c:choose>
						<c:when test="${(memberJSP.getSubscription().toString() eq 'VIP')}">
							<option value="VIP" selected>-- VIP --</option>
						</c:when>
						<c:otherwise>
							<option value="VIP">VIP</option>
						</c:otherwise>
					</c:choose>
	            </select>
	          </div>
	        </div>
	        <div class="row">
	          <div class="input-field col s12">
	            <input id="adresse" type="text" value="<c:out value="${memberJSP.getAddress()}"/>" name="adresse"> <!-- TODO : remplacer adresseDuMembre par l'adresse du membre -->
	            <label for="adresse">Adresse</label>
	          </div>
	        </div>
	        <div class="row">
	          <div class="input-field col s6">
	            <input id="email" type="email" value="<c:out value="${memberJSP.getEmail()}"/>" name="email"> <!-- TODO : remplacer emailDuMembre par l'email du membre -->
	            <label for="email">E-mail</label>
	          </div>
	          <div class="input-field col s6">
	            <input id="telephone" type="tel" value="<c:out value="${memberJSP.getPhone()}"/>" name="telephone"> <!-- TODO : remplacer telephoneDuMembre par le t�l�phone du membre -->
	            <label for="telephone">Telephone</label>
	          </div>
	        </div>
	        <div class="row center">
	          <button class="btn waves-effect waves-light" type="submit">Enregistrer</button>
	          <button class="btn waves-effect waves-light orange" type="reset">Annuler</button>
	        </div>
	      </form>
	      
	      <form action="/Projet-Ric-David/membre_delete" method="get" class="col s12">
	        <input type="hidden" value="<c:out value="${memberJSP.getId()}"/>" name="id"> <!-- TODO : remplacer idDuMembre par l'id du membre -->
	        <div class="row center">
	          <button class="btn waves-effect waves-light red" type="submit">Supprimer le membre
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
                  <th>Livre</th>
                  <th>Date d'emprunt</th>
                  <th>Retour</th>
                </tr>
              </thead>
              <tbody id="results">
				<c:if test="${! empty lendingListJSP}">
                    <c:forEach var="loan" items="${lendingListJSP}">
                      <tr>
                        <td><c:out value="${loan.getBook().getTitle()}"/> <c:out value="${loan.getBook().getAuthor()}"/></td>
                        <td><c:out value="${loan.getLendDate()}"/></td>
                        <td>
							<a href="emprunt_return?id=${loan.getId()}">edit<ion-icon class="table-item" name="log-in"></a>
                        </td>													
                      </tr>
                    </c:forEach>
				</c:if>
				<!-- TODO : parcourir la liste des emprunts en cours pour ce membre et les afficher selon la structure d'exemple ci-dessus -->
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
