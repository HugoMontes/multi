
    PrimeFaces.locales['es'] = {
		closeText : 'Este tambien', //Cerrar
		prevText : 'Anterior',
		nextText : 'Siguiente',
		currentText : 'Borra este Boton', // Inicio
		monthNames : [ 'Enero','Febrero','Marzo','Abril','Mayo','Junio','Julio','Agosto','Septiembre','Octubre','Noviembre','Diciembre' ],
		monthNamesShort : [ 'Ene','Feb','Mar','Abr','May','Jun','Jul','Ago','Sep','Oct','Nov','Dic' ],
		dayNames : [ 'Domingo','Lunes','Martes','Miercoles','Jueves','Viernes', 'Sábado' ],
		dayNamesShort : [ 'Dom', 'Lun', 'Mar', 'Mie', 'Jue', 'Vie', 'Sab' ],
		dayNamesMin : [ 'D', 'L', 'M', 'M', 'J', 'V', 'S' ],
		weekHeader : 'Semana',
		firstDay : 1,
		isRTL : false,
		showMonthAfterYear : false,
		yearSuffix : '',
		timeOnlyTitle : 'Solo hora',
		timeText : 'Tiempo',
		hourText : 'Hora',
		minuteText : 'Minuto',
		secondText : 'Segundo',
		ampm : false,
		month : 'Mes',
		week : 'Semana',
		day : 'Dia',
		allDayText : 'Todo el dia'
	};

function stopRKey(evt) {
    var evt = (evt) ? evt : ((event) ? event : null);
    var node = (evt.target) ? evt.target : ((evt.srcElement) ? evt.srcElement : null);
//    if (evt.keyCode == 13)  {alert('Que te pasa porque apretas Enter...' + node.type);return false;}
    if ((evt.keyCode == 13) && (node.type=="text"))  {return false;}
}

document.onkeypress = stopRKey;

function trimText(txt) {
    txt.value = txt.value.toUpperCase();
}
function mayusText(txt) {
    txt.value = txt.value.toUpperCase();
}
function trimIndex(txt) {
    txt.value = txt.value.replace(/^\s+|\s+$/g, '');
}

function trimTextMinusculas(txt) {
    txt.value = txt.value.replace(/^\s+|\s+$/g, '');
}

function atrasNo() {
	if (window.history) {
		function noBack() {
			window.history.forward()
		}
		noBack();
		window.onload = noBack;
		window.onpageshow = function(evt) {
			if (evt.persisted)
				noBack()
		}
		window.onunload = function() {
			void (0)
		}
	}
}

function noBack() {
	window.history.forward(1)
}
noBack();
atrasNo();
window.onload = noBack;
window.onload = atrasNo;
window.status = 'Mensaje de estatus::..';
window.onpageshow = function(evt) {
	if (evt.persisted)
		noBack();
}
window.onunload = function() {
	void (0);
}
window.onload = function() {
	detectarNavegador();
}

function detectarNavegador() {
	if (navigator.appName != "Netscape") {
		alert("No esta utilizando un Navegador compatible, por favor inicie la session con Mozilla Firefox");
	}
}

if (window.document.addEventListener) {
	window.document.addEventListener("keydown", avoidInvalidKeyStorkes, false);
} else {
	window.document.attachEvent("onkeydown", avoidInvalidKeyStorkes);
	document.captureEvents(Event.KEYDOWN);
}


function avoidInvalidKeyStorkes(evtArg) {
	var evt = (document.all ? window.event : evtArg);
	var isIE = (document.all ? true : false);
	var KEYCODE = (document.all ? window.event.keyCode : evtArg.which);
	var CTRL = (document.all ? window.event.ctrlKey : evtArg.ctrlKey);
	var ALT = (document.all ? window.event.altKey : evtArg.altKey);
	var element = (document.all ? window.event.srcElement : evtArg.target);
	if (KEYCODE == 8) { // tecla backspace
		if (element.type == "text" || element.type == "textarea"
				|| element.type == "password" || element.type == "file") {
			return true;
		} else {
			if (isIE) {
				evt.keyCode = 0;
				evt.returnValue = false;
				window.status = msg;
			} else {
				evt.preventDefault();
				evt.stopPropagation();
			}
			return false;
		}
	}
	if (CTRL) { // permitir copiar y pegar
		return true;
//		if (KEYCODE == 67 || KEYCODE == 99 || // Ctrl+C permitir copiar
//		KEYCODE == 86 || KEYCODE == 118 || // Ctrl+V permitir pegar
//		KEYCODE == 88 || KEYCODE == 120 ||  // Ctrl+X permitir cortar
//		KEYCODE == 81 || KEYCODE == 113 || KEYCODE == 17 ) { // Ctrl+Q = @
//			return true;
//		} else {
//			if (isIE) {
//				evt.returnValue = false;
//				evt.keyCode = 0;
//				window.status = msg;
//			} else {
//				evt.preventDefault();
//				evt.stopPropagation();
//			}
//			return false;
//		}
	}
	if (ALT) {
		if (isIE) {
			evt.returnValue = false;
			evt.keyCode = 0;
			window.status = msg;
		} else {
			evt.preventDefault();
			evt.stopPropagation();
		}
	}
	switch (KEYCODE) {
	case 112: // F1
	case 113: // F2
	case 114: // F3
	case 115: // F4
	case 116: // F5
	case 117: // F6
	case 118: // F7
	case 119: // F8
	case 120: // F9
	case 121: // F10
	case 122: // F11
	case 123: // F12
	case 27: // ESCAPE
		if (isIE) {
			evt.returnValue = false;
			evt.keyCode = 0;
			window.status = msg;
		} else {
			evt.preventDefault();
			evt.stopPropagation();
		}
		break;
	default:
		window.status = "Done";
	}
}

function isNumeroDecimalKey(evt)
{
     var charCode = (evt.which) ? evt.which : event.keyCode
     if (charCode > 31  && (charCode < 48 || charCode > 57) 
         && (charCode < 45 || charCode > 47))
        return false;
     	return true;
}

function isNumeroIntegerKey(evt)
{
     var charCode = (evt.which) ? evt.which : event.keyCode
     if (charCode > 31  && (charCode < 48 || charCode > 57))
        return false;
     return true;
}

// Funcion que corrige la insertcion de @ en 
// entradas de texto de PrimeFaces
// @author flinares
function parcheArroba(elEvento, textInput) {

	var evento = elEvento || window.event;
	 
	if (typeof evento !== 'undefined') {
		if ( typeof evento.charCode !== 'undefined' ) {
			if (evento.charCode==64){
				textInput.value = textInput.value + "@";
				return true;
			}
		}
	}
	return true;

}
