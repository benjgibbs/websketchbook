<!doctype HTML>
<head>
<link rel="stylesheet" href="/css/bootstrap.min.css">
<link rel="stylesheet" href="/css/bootstrap-theme.min.css">
<link rel="stylesheet" href="/css/grid.css">

<script src="/js/jquery-2.1.4.js"></script>
<script src="/js/bootstrap.min.js"></script>
<style>
	form {
		text-align: left;
	}
	.input {
		text-align: left;
	}
</style>

</head>
<body>
<div class="container-fluid">
	<div class="row">
		<div class="col-md-2"> </div>
		<div class="col-md-9">
			<h2 class="input">Letters</h3>
			<form action="/countdown" method="GET">
				<input type="text" name="letters">
				<input type="submit">
			</form>
			<h2>Solutions</h3>
			<ol>
				<#list wordlist! as word>
				<li>${word}
			</#list>
			</ol>
			<#if error??>
				<div class="error">
					<h3>Error</h3>
					${error}
				</div>
			</#if>
		</div>
		<div class="col-md-1"> </div>
	</div>
</div>

</body>