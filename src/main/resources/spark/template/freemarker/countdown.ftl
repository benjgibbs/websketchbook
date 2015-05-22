<!doctype HTML>
<head>
<link rel="stylesheet" href="/css/bootstrap.min.css">
<link rel="stylesheet" href="/css/bootstrap-theme.min.css">
<link rel="stylesheet" href="/css/grid.css">

<script src="/js/jquery-2.1.4.js"></script>
<script src="/js/bootstrap.min.js"></script>
<style>
	form {
		text-align: center;
	}
	.input {
		text-align: center;
	}
</style>

</head>
<body>
<h3 class="input">Letters</h3>
<form action="/countdown" method="GET">
<input type="text" name="letters">
<input type="submit">
</form>
<h3>Solutions</h3>
<ul>
<#list wordlist! as word>
<li>${word}
</#list>
</ul>
 
<#if error??>
<div class="error">
<h3>Error</h3>
${error}
</div>
</#if>
</body>