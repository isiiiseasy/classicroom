//--------------------タブ機能--------------------
function switchTab(target, tabId) {
    Array.prototype.slice.call(document.getElementsByClassName('tab-contents')).forEach(function(e) {
        e.style.display = 'none';
    });
    Array.prototype.slice.call(document.getElementsByClassName('tab-buttons')).forEach(function(e) {
        e.classList.remove('active');
    });
    document.getElementById(tabId).style.display = 'block';
    target.classList.add('active');
}
