<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<!DOCTYPE html>
<html>
    <head>
        <title>チャット</title>
        <link href="<%= request.getContextPath() %>/css/main.css" rel="stylesheet" type="text/css" media="screen" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
        <script type="text/javascript">
            $(function(){
                connect();
                $("#btn").click(button)
            });
            /* 接続 */
            function connect(){
                $.ajax({
                    url:"./polling",
                    type:"GET",
                    data:{},
                    complete: fin,
                    success:pushed
                });

            }
            /* 投稿 */
            function pushed(data, status, hxr){
                $t = $("<div>").wrapInner(data);
                $("#result").append($t);
            }
            /* 接続終了の再接続 */
            function fin(hxr, status){
                connect();
            }
            /* ボタンが押されたときの処理 */
            function button(){
                $.post("./push", {text: $("#txt").val()});
                $("#txt").val("");
            }
        </script>
    </head>
    <body>
        <h1 id="chat_main">チャットだよん</h1>

        <script>
			function writeSignature() {
			    var xhr = new XMLHttpRequest();
			    xhr.addEventListener("load", function(){
			        var e = document.getElementById("*");
			        for (var i = 0, j; j = e[i]; i++) {
			            if (j.className === "signature") {
			                j.innerHTML = xhr.responseText;
			            }
			        }
			    });
			    xhr.open("get", "./../../../chatlog.txt");
			    xhr.send();
			}

			function Init() {
			    writeSignature();
			}

			window.onload = Init;
		</script>

		<pre class="signature"></pre>
        <div id="result" class="outputchat"></div>
        <input type="text" id="txt"/><input type="submit" id="btn"/>
    </body>
</html>
