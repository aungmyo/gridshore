<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>Raffle application using the spring mvc toolbox</title>

    <link rel="stylesheet" href="${ctx}/styles/raffle.css" type="text/css"/>
    <script type="text/javascript" src="${ctx}/js/jquery-1.2.1.min.js"></script>
    <script type="text/javascript" src="${ctx}/js/jquery.dimensions.js"></script>
    <script type="text/javascript" src="${ctx}/js/my-custom-scripts.js"></script>
    <script type="text/javascript">
        var actualwinner = "${price.winner.participant.name}";
        var fakewinners = new Array(${notwinners}, actualwinner);
        var counter = 0;

        $(document).ready(function() {
            alert(fakewinners.toString());
            $("#link-winner").click(function() {
                doTheWinnerThingWithRaffleId(${price.raffle.id});
            });

            showWinner();
        });

        function showWinner() {
            if (counter < fakewinners.length) {
                myFader(fakewinners[counter++]);
            } else {
                $("#winner").css("color", "red").css("text-decoration", "none");
            }
        }

        function myFader(newText) {
            $("#winner").fadeOut(500, function() {
                $(this).html(newText).fadeIn(500, function() {
                    showWinner();
                });
            });
        }
    </script>
</head>

<body>
<div id="screentitle">Master Raffle Application</div>
<div id="sidemenubox">
    <ul>
        <li><a href="index.html">Back to main</a></li>
        <li><a href="#" id="link-winner">Back to prizes</a></li>
    </ul>
</div>
<div id="maincontentbox">

    <h2>Prize : ${price.title}</h2>

    <div id="winner">???</div>
</div>
</body>
</html>