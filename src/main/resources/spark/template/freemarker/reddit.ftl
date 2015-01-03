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
		<a href="${link.getUrl()}"><img src="${link.getThumb()}"></a> 
	</div>
	<div class="col-xs-8">
		<div><a href="${link.getUrl()}">${link.getTitle()}</a> </div>
		<div>${link.getCreated()}</div>
		<div><a href="http://${link.getDomain()}">${link.getDomain()}</a></div>
		<div><a href="http://reddit.com/u/${link.getAuthor()}">${link.getAuthor()}</a> </div>
	</div>
</div>
</#list> 

<#if error??>
<h2>Error</h2>
${error}
</#if>
</body>