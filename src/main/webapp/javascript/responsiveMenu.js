/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function openNav() {
    document.getElementById("menu").style.width = "300px";
    document.getElementById("main").style.marginLeft = "300px";
}

function closeNav() {
    document.getElementById("menu").style.width = "0";
    document.getElementById("main").style.marginLeft= "0";
}

window.onresize = function(event) {
    if($(window).width() < 1021){
        closeNav();
    }else{
        document.getElementById("menu").style.width = "auto";
    }
};