var answer = window.prompt('ヘルプを見ますか?');
if (answer === 'yes') {
	window.alert('タップでジャンプ、障害物をよけます。');
} else if (answer === 'no') {
	window.alert(ゲーム起動中...);
} else {
	window.alert('yesかnoでお答えください');
}

var number = Math.floor(Math.random() * 5);
var answer = parseInt(window.prompt('数当てゲーム。0～5の数字を入力してね。'));
var message;
if (answer === number) {
	message = 'あたり';	
} else if (answer < number) {
	message = '残念でした!もっと大きい';
} else if (answer < number) {
	message = '残念でした!もっと小さい';
} else {
	message='0～5の数字を入力してね。';
}
window.alert(message);

//3-5
var hour = new Date().getHours();
if (hour >= 19 && hour < 21) {
	window.alert('お弁当30%OFF');
} else if (hour === 9 || hour === 15) {
	window.alert('お弁当１個買ったら１個おまけ!');
} else {
	window.alert(''お弁当はいかがですか);
}

//3-6
for (var i = 1; i <= 10; i++) {
	console.log(i + '枚');
}

//3-7
var enemy = 100;
var attack;
var count = 0;
window.alert('戦闘スタート!');
while(enemy > 0) {
	attack = Math.floor(Math.random() * 30)+1;
	console.log('モンスターに' + attack + 'のダメージ!');
	enemy -= attack;
	count++;
}
console.log(count + '回でモンスターを倒した!');

//3-8
var total = function(price) {
	var tax = 0.08;
	return price + price * tax;
}
console.log('コーヒーメーカーの値段は' + total(8000) + '円(税込)です。');
document.getElementById('output').textContent = 'コーヒーメーカーの値段は' + total(8000) + '円(税込)です。';

//3-9
var todo = ['デザインかんぷ作成', 'データ整理', '勉強会申し込み', '牛乳買う'];
todo.push('歯医者に行く');
for (var i=0; i<todo.length; i++) {
	var li = document.createElement('li');
	li.textContent = todo[i];
	document.getElementById('list').appendChild(li);
}

//3-10がない
//3-11
var jsbook = {title: 'Javascript入門', price: 2500, stock: 3};
console.log(jsbook);
console.log(jsbook.title);
console.log(jsbook['price']);
jsbook.stock = 10;
console.log(jsbook.stock);

//4-1
document.getElementById("form").onsubmit = function() {
	console.log('クリックされました');
};
//4-2
document.getElementById("form").onsubmit = function() {
	var search = document.getElementById("form").word.value;
	document.getElementById('output').textContent = '「 ' + search + ' 」 '  + の検索中...';
	return false;
};



