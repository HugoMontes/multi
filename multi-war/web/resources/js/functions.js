if(!("console" in window)) 
    window.console = {log: function() {}};

function alfaNumerico(event){
	 var expAlphanumeric = /^([a-z]|[A-Z]|[\d]|[\s])*$/;
	
    var teclas_especiales = [8, 37, 39, 46,9];
    // 8 = BackSpace, 46 = Supr, 37 = flecha izquierda, 39 = flecha derecha, 9 tab
    var evento = event || window.event;
    var codigoCaracter = evento.charCode || evento.keyCode;
    var caracter = String.fromCharCode(codigoCaracter);

    var tecla_especial = false;
    for(var i in teclas_especiales) {
        if(codigoCaracter == teclas_especiales[i]) {
            tecla_especial = true;
            break;
        }
    }
   
    return expAlphanumeric.test(caracter) || tecla_especial;
}

function validaExisteComponente(id) {
		if(document.getElementById(id)){			
			return true;
		} else {
			return false;
		}
}

PrimeFaces.locales['es'] = {
	    closeText: 'Aceptar',
	    prevText: 'Anterior',
	    nextText: 'Siguiente',
	    monthNames: ['Enero','Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio', 'Julio', 'Agosto', 'Septiembre', 'Octubre', 'Noviembre', 'Diciembre'],
	    monthNamesShort: ['Ene', 'Feb', 'Mar', 'Abr', 'May', 'Jun','Jul','Ago','Sep','Oct','Nov','Dic'],
	    dayNames: ['Domingo','Lunes','Martes','Miércoles','Jueves','Viernes','Sábado'],
	    dayNamesShort: ['Dom','Lun', 'Mar', 'Mie', 'Jue', 'Vie', 'Sab'],
	    dayNamesMin: ['D','L','M','M','J','V','S'],
	    weekHeader: 'Semana',
	    firstDay: 1,
	    isRTL: false,
	    showMonthAfterYear: false,
	    yearSuffix: '',
	    timeOnlyTitle: 'Sólo hora',
	    timeText: 'Tiempo',
	    hourText: 'Hora',
	    minuteText: 'Minuto',
	    secondText: 'Segundo',
	    currentText: 'Ahora',
	    ampm: false,
	    month: 'Mes',
	    week: 'Semana',
	    day: 'Día',
	    allDayText : 'Todo el día'
	};	

PrimeFaces.locales ['en'] = {
	    closeText: 'Close',
	    prevText: 'Previous',
	    nextText: 'Next',
	    monthNames: ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December' ],
	    monthNamesShort: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec' ],
	    dayNames: ['Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday'],
	    dayNamesShort: ['Sun', 'Mon', 'Tue', 'Wed', 'Tue', 'Fri', 'Sat'],
	    dayNamesMin: ['S', 'M', 'T', 'W ', 'T', 'F ', 'S'],
	    weekHeader: 'Week',
	    firstDay: 1,
	    isRTL: false,
	    showMonthAfterYear: false,
	    yearSuffix:'',
	    timeOnlyTitle: 'Only Time',
	    timeText: 'Time',
	    hourText: 'Time',
	    minuteText: 'Minute',
	    secondText: 'Second',
	    currentText: 'Now',
	    ampm: false,
	    month: 'Month',
	    week: 'week',
	    day: 'Day',
	    allDayText: 'All Day'
	};

PrimeFaces.locales ['fr'] = {
	    closeText: 'Fermer',
	    prevText: 'Précédent',
	    nextText: 'Suivant',
	    monthNames: ['Janvier', 'Février', 'Mars', 'Avril', 'Mai', 'Juin', 'Juillet', 'Août', 'Septembre', 'Octobre', 'Novembre', 'Décembre' ],
	    monthNamesShort: ['Jan', 'Fév', 'Mar', 'Avr', 'Mai', 'Jun', 'Jul', 'Aoû', 'Sep', 'Oct', 'Nov', 'Déc' ],
	    dayNames: ['Dimanche', 'Lundi', 'Mardi', 'Mercredi', 'Jeudi', 'Vendredi', 'Samedi'],
	    dayNamesShort: ['Dim', 'Lun', 'Mar', 'Mer', 'Jeu', 'Ven', 'Sam'],
	    dayNamesMin: ['D', 'L', 'M', 'M', 'J', 'V', 'S'],
	    weekHeader: 'Semaine',
	    firstDay: 1,
	    isRTL: false,
	    showMonthAfterYear: false,
	    yearSuffix:'',
	    timeOnlyTitle: 'Choisir l\'heure',
	    timeText: 'Heure',
	    hourText: 'Heures',
	    minuteText: 'Minutes',
	    secondText: 'Secondes',
	    currentText: 'Maintenant',
	    ampm: false,
	    month: 'Mois',
	    week: 'Semaine',
	    day: 'Jour',
	    allDayText: 'Toute la journée'
	};

$(document).bind("contextmenu",function(e){
    return false;
});

var initialFormState;
var currentFormState;

function isModifiedFormForBack(form,idButon){
 setCurrentFormState(form);
 console.log(initialFormState);
 console.log(currentFormState);
 if(initialFormState!=currentFormState){	 
  cnfwBack.show();
  return false;
 } else {	 
	 document.getElementById(idButon).click();
 }
}

function isModifiedFormForSave(form,idButon){
 setCurrentFormState(form);
 console.log(initialFormState);
 console.log(currentFormState);
 if(initialFormState==currentFormState){	 
	 cnfwNoModified.show();
	 return false;   
 } else {
	 document.getElementById(idButon).click();
 }
}

function setInitialFormState(form){
 initialFormState=$(form).serialize();
}

function setCurrentFormState(form){
 currentFormState=$(form).serialize();
}

function maxLenghtDescription(fieldObj) {
	if(fieldObj.value.length < 200){
		return true;
	} else {
		return false;
	}
}

function isModifiedFormForBackMotive(idCombo,idButon){
	var combo = document.getElementById(idCombo);
	 if(combo.value!="" && combo.value!="-1"){
		 cnfwBackMotive.show();
		 return false;
	 } else {      		 
		 document.getElementById(idButon).click();
	 }
}
//codificate characters in unicode format for using by JS
function decodeToUtf8(text){
	return decodeURIComponent(escape(window.atob(text)));
}

var allSecurityHolidays;

function setAllSecurityHolidays(str){
	allSecurityHolidays = str;
}

function disableAllSecurityHolydays(date) {
	 // primefaces js widget day is from [0 - 6] - [Sunday - Monday]
	var weekDay = date.getDay();
	
	//If Weekend (Saturday or Sunday)
	if(weekDay == 0 || weekDay == 6) {
		return [false];
	}
	
	var disabledDays = allSecurityHolidays.toString().split(",");
   var m = date.getMonth();
   m = m + 1;	    
   var d = date.getDate();
   var y = date.getFullYear();

   var strMonth;
   if(m < 10) {
   	strMonth = "0" + m;
	} else {
		strMonth = m;
	}

	var strDay;
	if(d < 10) {
		strDay = "0" + d;
	} else {
		strDay = d;
	}
   
   for (i = 0; i < disabledDays.length; i++) {
       if($.inArray(strDay + '/' + strMonth + '/' + y,disabledDays) != -1) {
           return [false];
       }
   }
   return [true];
}


function fileuploadValidate(fUploadId,fUploadWidgerVar){
	$( "[id='"+fUploadId+"']" ).on( "change", function( e ) {
		var invalidFile=fUploadWidgerVar.content.find("ul li:last-child").html();
		//var invalidFile=fUploadWidgerVar.uploadContent.find("tr.ui-state-error:last").html();
		if(invalidFile!=undefined && invalidFile!=null){ 
			$('#rowFuplContent').html(invalidFile);
			dlgwFileUploadInvFile.show(); 
		}
	}); 
} 

function clearInvalidFileMsgFupl(fuplWidgetVar){
//	fuplWidgetVar.uploadContent.find("tr.ui-state-error").remove();
}

var isCtrlPresset
function ctrlPress(evt){
	//code 17 --> ctrl
	evt = (evt) ? evt : event;	
	var code=(evt.keyCode ? evt.keyCode : evt.which);
	if(code == 17){
		isCtrlPresset = false;
	}
}
function validateCtrlSpace(evt,input){
	//code 17 --> ctrl
	//code 32 --> blank space
	evt = (evt) ? evt : event;	
	var code=(evt.keyCode ? evt.keyCode : evt.which);
	if(code == 17){
		isCtrlPresset = true;
	}
	if((code != 17 && isCtrlPresset == true) 
			|| (input.value.length == 0 && code == 32 ) 
			|| (input.value.length > $(input).caret().begin && input.value.charAt($(input).caret().begin)==" " && code == 32)
			|| ($(input).caret().begin > 0 && input.value.charAt(($(input).caret().begin)-1)==" " && code == 32)){
		return false;
	}
}
