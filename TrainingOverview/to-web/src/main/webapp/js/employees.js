/*
 *   JavaScript functions related to employee handling.
 */
function fillEmployeeBox(employeeId) {
    $("#detailscontentbox").load("employee.view",{employeeId : employeeId}, function() {
        $(".zebra tr:nth-child(even)").addClass("striped");
        $("#detailscontentbox").show();
    });
}

function showTrainingPlan(employeeId) {
    $("#detailscontentbox").load("showplannedtraining.view",{employeeId : employeeId}, function() {
        $(".zebra tr:nth-child(even)").addClass("striped");
        $("#detailscontentbox").show();
    });
}

function addTrainingPlan(employeeId,trainingsessionId) {
    $("#detailscontentbox").load("addplannedtraining.view",
                                {employeeId : employeeId, trainingsessionId : trainingsessionId},
                                function() {
        $(".zebra tr:nth-child(even)").addClass("striped");
        $("#detailscontentbox").show();
    });
}

function showTrainingWishes(employeeId) {
    $("#detailscontentbox").load("showwishedtraining.view",{employeeId : employeeId}, function() {
        $(".zebra tr:nth-child(even)").addClass("striped");
        $("#detailscontentbox").show();
    });
}

function addTrainingWish(employeeId,trainingId) {
    $("#detailscontentbox").load("addwishedtraining.view",
                                {employeeId : employeeId, trainingId : trainingId},
                                function() {
        $(".zebra tr:nth-child(even)").addClass("striped");
        $("#detailscontentbox").show();
    });
}
