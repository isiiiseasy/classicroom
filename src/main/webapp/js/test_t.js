document.addEventListener('DOMContentLoaded', function() {
    getSectionData();
});

function getSectionData() {
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function() {
        if (xhr.readyState == 4 && xhr.status == 200) {
            var data = JSON.parse(xhr.responseText);
            makeSubjectList(data);
        }
    };
    xhr.open('GET', 'api/sections');
    xhr.send();
}

function makeSubjectList(data) {
    var testList = document.getElementById('test-list');
    Array.prototype.slice.call(testList.children).forEach(function(child) {
        child.parentNode.removeChild(child);
    });
    data.forEach(function(subject) {
        var DOMSubject = document.createElement('li');
        var DOMInnerList = document.createElement('ol');
        DOMInnerList.id = 'test-list-' + subject.subject_id;
        DOMSubject.textContent = subject.subject_name;
        var DOMTestAdd = document.createElement('a');
        DOMTestAdd.textContent = ' [テストを追加]';
        DOMTestAdd.addEventListener('click', addTest.bind(null, subject.subject_id));
        DOMSubject.appendChild(DOMTestAdd);

        DOMSubject.appendChild(DOMInnerList);
        testList.appendChild(DOMSubject);
    });
    getTestData();
}

function getTestData() {
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function() {
        if (xhr.readyState == 4 && xhr.status == 200) {
            var data = JSON.parse(xhr.responseText);
            makeTestList(data);
        }
    };
    xhr.open('GET', 'api/tests');
    xhr.send();
}


function makeTestList(data) {
    data.forEach(function(test) {
        var DOMInnerList = document.getElementById('test-list-' + test.subject_id) ;
        var DOMTest = document.createElement('li');
        DOMTest.textContent = test.test_name;
        var DOMTestDelete = document.createElement('a');
        DOMTestDelete.textContent = ' [削除]';
        DOMTestDelete.addEventListener('click', deleteTest.bind(null, test.test_id));
        DOMTest.appendChild(DOMTestDelete);

        DOMInnerList.appendChild(DOMTest);
    });
}

function addTest(subjectId) {
    var testName = window.prompt('新しいテスト名');
    var d = new Date();
    if (testName) {
        requestTestAPI('POST', '&subject-id=' + encodeURIComponent(subjectId) + '&test-name=' + encodeURIComponent(testName) + '&test-date=' + d.getFullYear() + '-' + (d.getMonth()+1) + '-' + d.getDate());
    } else {
        window.alert('キャンセルされました');
    }
}

function deleteTest(testId) {
    if (window.confirm('本当によろしいですか？')) {
        requestTestAPI('DELETE', '&test-id=' + encodeURIComponent(testId));
    } else {
        window.alert('キャンセルされました');
    }
}

function requestTestAPI(method, param) {
    var xhr = new XMLHttpRequest();

    xhr.onreadystatechange = function() {
        if (xhr.readyState == 4 && xhr.status == 200) {
            var data = JSON.parse(xhr.responseText);
            getSectionData(data);
        }
    };
    xhr.open(method, 'api/tests?' + param);
    xhr.send();
}
