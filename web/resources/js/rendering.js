let global_dots = [];

function loadDots() {
    let xhr = new XMLHttpRequest();
    xhr.open('GET', 'rest/point/getpoints', false);
    xhr.send();
    if (xhr.status != 200)
        alert("Get error");
    else
        global_dots = JSON.parse(xhr.responseText);


    let current_r = global_dots[global_dots.length - 1].r;
    if (current_r !== null){
        drawCanwas('canvas', current_r);
        $('#' + current_r).checked = true;
    } else {
        drawCanwas('canvas', 2);
        document.getElementById("#2").checked = true;
    }

}


function drawDots() {
    for (let i = 0; i < global_dots.length; i++) {
        let r = global_dots[i].r;
        let x = (global_dots[i].x * 130 / r) + 150;
        let y = -((global_dots[i].y * 130 / r) - 150);
        drawPoint("canvas", x, y, global_dots[i].goal)
    }
}

let app = angular.module("myApp",[]);

app.controller('resultController', ['$scope', '$http', function ($scope, $http) {

    $scope.show_table = function () {
        let btn = document.getElementById('btn-dots-table');
        let text = btn.value;
        if (text === 'Показать таблицу точек') {
                $scope.dots = global_dots;
                btn.value = 'Скрыть таблицу точек';

        } else {
            $scope.dots = [];
            btn.value = 'Показать таблицу точек';
        }
    };

}]);


//--------------------------------------------------------------------
function drawCanwas(id, r) {

    let canvas = document.getElementById(id),
        context = canvas.getContext("2d");

    let Ox = canvas.width / 2;
    let Oy = canvas.height / 2;
    r = Number(r);

    //очистка
    context.clearRect(0, 0, canvas.width, canvas.height);

    //прямоугольник
    context.beginPath();
    context.rect(20, 20, 130, 130);
    context.closePath();
    context.strokeStyle = "blue";
    context.fillStyle = "blue";
    context.fill();
    context.stroke();

    // сектор
    context.beginPath();
    context.moveTo(150, 150);
    context.arc(150, 150, 130, -Math.PI, Math.PI / 2, true);
    context.closePath();
    context.strokeStyle = "blue";
    context.fillStyle = "blue";
    context.fill();
    context.stroke();

    //треугольник
    context.beginPath();
    context.moveTo(150, 150);
    context.lineTo(215, 150);
    context.lineTo(150, 215);
    context.lineTo(150, 150);
    context.closePath();
    context.strokeStyle = "blue";
    context.fillStyle = "blue";
    context.fill();
    context.stroke();

    //отрисовка осей
    context.beginPath();
    context.strokeStyle = "black";
    context.fillStyle = "black";
    context.font = "10px Verdana";
    context.moveTo(150, 0);
    context.lineTo(150, 300);
    context.moveTo(150, 0);
    context.lineTo(145, 15);
    context.moveTo(150, 0);
    context.lineTo(155, 15);
    context.fillText("Y", 160, 10);
    context.moveTo(0, 150);
    context.lineTo(300, 150);
    context.moveTo(300, 150);
    context.lineTo(285, 145);
    context.moveTo(300, 150);
    context.lineTo(285, 155);
    context.fillText("X", 290, 135);

    // деления X
    context.moveTo(145, 20);
    context.lineTo(155, 20);
    context.fillText(r, 160, 20);
    context.moveTo(145, 85);
    context.lineTo(155, 85);
    context.fillText((r / 2), 160, 78);
    context.moveTo(145, 215);
    context.lineTo(155, 215);
    context.fillText(-(r / 2), 160, 215);
    context.moveTo(145, 280);
    context.lineTo(155, 280);
    context.fillText(-r, 160, 280);
    // деления Y
    context.moveTo(20, 145);
    context.lineTo(20, 155);
    context.fillText(-r, 20, 170);
    context.moveTo(85, 145);
    context.lineTo(85, 155);
    context.fillText(-(r / 2), 70, 170);
    context.moveTo(215, 145);
    context.lineTo(215, 155);
    context.fillText((r / 2), 215, 170);
    context.moveTo(280, 145);
    context.lineTo(280, 155);
    context.fillText(r, 280, 170);

    context.closePath();
    context.strokeStyle = "black";
    context.fillStyle = "black";
    context.stroke();

    //отрисовка сохраненных точек
    drawDots ();
}

function clicCanvas(canv_Id, R) {
    let elem = document.getElementById(canv_Id);
    let br = elem.getBoundingClientRect();
    let left = br.left;
    let top = br.top;
    let event = window.event;
    let x = event.clientX - left;
    let y = event.clientY - top;
    let boolArea = isArea(x, y, R);
    drawPoint(canv_Id, x, y, boolArea);
}

function isArea(x, y, R) {
    x = R * (x - 150) / 130;
    y = R * (150 - y) / 130;
    if (x <= 0 && y >= 0 && x >= (-R) && y <= R)
        return 'true';
    if (x >= 0 && y <= 0 && y >= (x - 0.5 * R))
        return 'true';
    if (x <= 0 && y <= 0 && x * x + y * y <= R * R)
        return 'true';
    return 'false';
}

function drawPoint(id, x, y, isGoal) {
    let canvas = document.getElementById(id),
        context = canvas.getContext("2d");
    context.beginPath();
    context.arc(x, y, 2, 0, 2 * Math.PI, false);
    context.closePath();
    if (isGoal === 'true' || isGoal === true) {
        context.strokeStyle = "green";
        context.fillStyle = "green";
    } else {
        context.strokeStyle = "red";
        context.fillStyle = "red";
    }
    context.fill();
    context.stroke();
}
