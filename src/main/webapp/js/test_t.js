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
        DOMTest.addEventListener('click', getQuestions.bind(DOMTest, test.test_id));
        DOMTest.textContent = test.test_name;
        var DOMTestDelete = document.createElement('a');
        DOMTestDelete.textContent = ' [削除]';
        DOMTestDelete.addEventListener('click', deleteTest.bind(null, test.test_id));
        DOMTest.appendChild(DOMTestDelete);
        if (!test.public_flg) {
            var DOMTestPublish = document.createElement('a');
            DOMTestPublish.textContent = ' [公開する]';
            DOMTestPublish.addEventListener('click', publishTest.bind(null, test.test_id));
            DOMTest.appendChild(DOMTestPublish);
        }


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

function publishTest(testId) {
    if (window.confirm('テストを公開しますか？')) {
        requestTestAPI('PUT', '&test-id=' + encodeURIComponent(testId) + '&public-flg=true');
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


function makeQuestionList(data) {
    var list = document.getElementById('question-list');
    Array.prototype.slice.call(list.children).forEach(function(child) {
        child.parentNode.removeChild(child);
    });
    for (var i = 0; i < data.length; i++) {
        var li = document.createElement('li');
        var q = document.createElement('textarea');
        q.classList.add('question');
        q.textContent = data[i].question;
        li.appendChild(q);
        li.appendChild(document.createElement('br'));
        for (var j = 0; j < 4; j++) {
            var radio = document.createElement('input');
            radio.setAttribute('type', 'radio');
            radio.classList.add('answer');
            radio.name = 'answer' + i;
            radio.value = j;
            if (j == data[i].answer) {
                radio.checked = true;
            }
            li.appendChild(radio);
            var c = document.createElement('input');
            c.value = data[i].choices[j];
            c.classList.add('choice');
            li.appendChild(c);
        }
        list.appendChild(li);
    }
}

function addQuestion() {
    var list = document.getElementById('question-list');
    var i = list.childElementCount;
    var li = document.createElement('li');
    var q = document.createElement('textarea');
    q.classList.add('question');
    li.appendChild(q);
    li.appendChild(document.createElement('br'));
    for (var j = 0; j < 4; j++) {
        var radio = document.createElement('input');
        radio.setAttribute('type', 'radio');
        radio.classList.add('answer');
        radio.name = 'answer' + i;
        radio.value = j;
        li.appendChild(radio);
        var c = document.createElement('input');
        c.classList.add('choice');
        li.appendChild(c);
    }
    list.appendChild(li);
}

function deleteQuestion() {
    var list = document.getElementById('question-list');
    list.removeChild(list.lastChild);
}

function getQuestions(test_id) {
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function() {
        if (xhr.readyState == 4 && xhr.status == 200) {
            var data;
            try {
                data = JSON.parse(xhr.responseText);
            } catch (e) {
                data = [];
            }
            makeQuestionList(data);
            document.getElementById('submit-question').onclick = submitQuestion.bind(null, test_id);
        }
    };
    xhr.open('GET', 'api/testcontents?test-id=' + test_id);
    xhr.send();
    document.getElementById('test-title').textContent = this.textContent;
}

function submitQuestion(test_id) {
    var parameter = 'test-id=' + test_id + '&data=';
    var data = [];
    var list = document.getElementById('question-list');
    Array.prototype.slice.call(list.children).forEach(function(child) {
        var question = {};
        question.question = child.getElementsByClassName('question')[0].value || '';
        question.choices = [];
        for (var i = 0, c = child.getElementsByClassName('choice'); i < 4; i++) {
            question.choices.push(c[i].value);
        }
        for (var j = 0, a = child.getElementsByClassName('answer'); j < 4; j++) {
            if (a[j].checked) {
                question.answer = a[j].value;
            }
        }
        data.push(question);
    });
    parameter += encodeURIComponent(JSON.stringify(data));

    var post = new XMLHttpRequest();
    post.onreadystatechange = function() {
        if (post.readyState == 4 && post.status == 200) {
            var data;
            try {
                data = JSON.parse(post.responseText);
            } catch (e) {
                data = [];
            }
            makeQuestionList(data);
        }
    };
    post.open('POST', 'api/testcontents');
    post.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    post.send(parameter);
}


document.addEventListener('DOMContentLoaded', function() {
    document.getElementById('add-question').addEventListener('click', addQuestion);
    document.getElementById('delete-question').addEventListener('click', deleteQuestion);
});
