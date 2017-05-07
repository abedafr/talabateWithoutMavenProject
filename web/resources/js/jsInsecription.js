/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function openNav() {
    document.getElementById("bodynav").style.width = "100%";
    document.getElementById("bodynav").style.visibility="visible";
    document.getElementById("bodynav").style.backgroundColor = "rgba(0,0,0,0.4)";
    document.getElementById("mySidenav").style.width = "200px";
    document.getElementById("main").style.marginLeft = "200px";
    document.body.style.backgroundColor = "rgba(0,0,0,0.4)";
}

function closeNav() {
    document.getElementById("bodynav").style.opacity = "100%";
    document.getElementById("bodynav").style.visibility="hidden";
    document.getElementById("bodynav").style.width = "0";
    document.getElementById("mySidenav").style.width = "0";
    document.getElementById("main").style.marginLeft = "0";
    document.body.style.backgroundColor = "white";
}


