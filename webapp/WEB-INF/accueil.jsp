<%@ page import="java.util.Vector"%>
<%@ page import="resto.*"%>
<%
Vector<Categorie> listeCategorie = (Vector<Categorie>) request.getAttribute("listeCategorie");
Vector<Object[]> listeProduit = (Vector<Object[]>) request.getAttribute("listeProduitParCategorie");
Vector<Produit> listeCommande = (Vector<Produit>) request.getSession().getAttribute("commandeEnCours");
Boolean lounge = ((Latabatra) request.getSession().getAttribute("latabatra")).getLounge();
%>
<div class="container-fluid" style="margin-top: 25px;">
	<div class="row" style="background-color: white;">
		<div class="col-12 col-md-4">
			<input placeholder="Rechercher" class="form-control">
		</div>
		<div class="col-12 col-md-2">
			<select class="form-control">
				<option value="0">Tous</option>
				<%
				for (int i = 0; i < listeCategorie.size(); i++) {
				%>
				<option value="<%=listeCategorie.get(i).getId()%>"><%=listeCategorie.get(i).getNom()%></option>
				<%
				}
				%>
			</select>
		</div>
	</div>
	<div class="row" style="margin-top: 15px;">
		<div style="background-color: white;" class="col-12 col-lg-9">
			<p>Liste produits</p>
			<%
			for (int i = 0; i < listeProduit.size(); i++) {
				Vector<Produit> lisP = (Vector<Produit>) listeProduit.get(i)[1];
			%>
			<div>
				<h3><%=listeProduit.get(i)[0]%></h3>
				<hr>
				<div class="row">
					<%
					for (int j = 0; j < lisP.size(); j++) {
					%>
					<div idfood="<%=lisP.get(j).getId()%>" class="card food"
						style="width: 18rem;">
						<img class="card-img-top" src="images/food_icon.jpg"
							alt="Card image cap">
						<div class="card-body">
							<h5 class="card-title"><%=lisP.get(j).getNom()%></h5>
							<p class="card-text">
								<%
								out.println(lisP.get(j).getDescription() != null ? lisP.get(j).getDescription() : "");
								%>
							</p>
						</div>
					</div>
					<%
					}
					%>
				</div>
			</div>
			<%
			}
			%>

		</div>
		<div style="background-color: white;" class="col-12 col-lg-3">
			<p>Vos commandes</p>
			<hr>
			<table style="margin : auto; width: 90%;" id="listeCommande">
				<tr>
					<th>Nom</th>
					<th>Prix</th>
				</tr>
				<%
				int i = 0;
				for (i = 0; i < listeCommande.size(); i++) {
				%>
					<tr  class="details" index="<%=i%>">
						<td><%=listeCommande.get(i).getNom()%></td>
						<td><%= lounge?listeCommande.get(i).getPrixLounge():listeCommande.get(i).getPrix() %></td>
					</tr>
				<%
				}
				%>
			</table>
		</div>
	</div>
</div>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
	$(document).ready(
			function() {
				var index =
<%=i - 1%>;
				var nouveauDetail = 0;
	;
				var cards = document.getElementsByClassName('food');
				for (var i = 0; i < cards.length; i++) {
					cards[i].addEventListener('click', function() {
						$.ajax({
							url : "ajout",
							type : "post",
							data : "food=" + this.getAttribute('idfood'),
							success : function(response) {
								if (response != 0) {
									console.log(response);
									var split = response.split("&");
									listeCommande.innerHTML += "<tr id='detail"+(++nouveauDetail)+"' index='"
									+ (++index) + "' class='details' ><td>"
											+ split[0] + "</td><td>"+split[1]+"</td></tr>";
											var details = document.getElementsByClassName('details');
											for (var i = 0; i < details.length; i++) {
												details[i].addEventListener('click', function() {
													var ceDetail = this;
													$.ajax({
														url : "retirer",
														type : "post",
														data : "index=" + this.getAttribute('index'),
														success : function(response) {
															for (var j= ceDetail.getAttribute('index'); j<details.length; j++) {
																details[j].setAttribute('index', details[j].getAttribute('index') - 1);
															}
															ceDetail.remove();
														},
													});
												});
											}
								} else {
									console.log("erreur");
								}
							},
						});
					});
				}

				var details = document.getElementsByClassName('details');
				for (var i = 0; i < details.length; i++) {
					details[i].addEventListener('click', function() {
						var ceDetail = this;
						$.ajax({
							url : "retirer",
							type : "post",
							data : "index=" + this.getAttribute('index'),
							success : function(response) {
								for (var j= ceDetail.getAttribute('index'); j<details.length; j++) {
									details[j].setAttribute('index', details[j].getAttribute('index') - 1);
								}
								ceDetail.remove();
							},
						});
					});
				}
			});
</script>
