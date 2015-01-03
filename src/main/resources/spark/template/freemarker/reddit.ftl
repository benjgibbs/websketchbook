<!doctype HTML>
<head>
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/bootstrap-theme.min.css">
<link rel="stylesheet" href="css/grid.css">

<script src="js/jquery-2.1.3.js"></script>
<script src="js/bootstrap.min.js"></script>
</head>
<body>
<h1>Links from ${subreddit}</h1>
<#list listoflinks! as link>
<div class="row">
	<div class="col-xs-4 thumb"> 
		<img src="${link.getThumb()}"> 
	</div>
	<div class="col-xs-8">
		<div><a href="${link.getUrl()}">${link.getTitle()}</a> </div>
		<div>${link.getCreated()}</div>
		<div>${link.getDomain()}</div>
		<div>${link.getAuthor()}</div>
	</div>
</div>
</#list> 

<#if error??>
<h2>Error</h2>
${error}
</#if>
</body>