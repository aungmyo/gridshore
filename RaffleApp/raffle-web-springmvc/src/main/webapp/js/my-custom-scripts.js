function doTheWinnerThing() {
    $("#maincontentbox").load("winner.view", {}, function() {
        ajaxCallbackForWinnerThing();
    });
}

function doTheWinnerThingWithRaffleId(raffleId) {
    $("#maincontentbox").load("winner.view", {raffle_id:raffleId}, function() {
        ajaxCallbackForWinnerThing();
    });
}

function ajaxCallbackForWinnerThing() {
    $("#choosenRaffle").change(function() {
        var raffleId = this.value;
        if (raffleId != "") {
            $("#choosenPrize").load("winnerprizes.view", {raffle_id : raffleId}, function() {
                $("#prizesdata tr:nth-child(even)").addClass("striped");
            });
        }
    }).change();
}