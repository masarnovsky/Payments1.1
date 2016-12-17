/*
function setModal(id, cash) {
    $('.modal').find('#id').text(id);
    $('.modal').find('#cash').text(cash);
    //var idAccountModal = document.getElementById('idAccount');
    //idAccountModal.setAttribute("value", id);
    //var accountCashModal = document.getElementById('accountCash');
    //accountCashModal.setAttribute("value", cash);

    var divWithHiddenInputs = document.getElementById('divWithHiddenInputs');
    divWithHiddenInputs.innerHTML = "<input id='idAccountModal' name='idAccount' type='hidden' value='" + id + "'>";

    console.dir(divWithHiddenInputs);
    console.log(divWithHiddenInputs)
    $('.modal').modal();
}
*/

$(document).ready(function(){
    // the "href" attribute of .modal-trigger must specify the modal ID that wants to be triggered
    $('.modal').modal();
});
