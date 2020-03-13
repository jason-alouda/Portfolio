// Copyright 2019 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     https://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

/**
 * Adds a random quote to the page.
 */
function addRandomQuote() {
  const quotes =
      ['Some infinities are greater than others', 'I think happiness is being content\
       with who you are and what you have, whether it is the people around you, the\
        things you do, the things you own, your lifestyle, your habits, your achievements,\
         your failures, your flaws, your qualities, your goals, your mission, and your life.\
          It is what you settle for when you can’t find anything better, or until you find\
           something better. I’m not saying that being complacent will make you happy, but\
            being able to enjoy what you have in the moment at least, will make you happy,\
             always.', 'It is vacuously true that if 1+1=3, then I am Avicii.',
              'qedsymbol'];

  // Pick a random quote.
  const quote = quotes[Math.floor(Math.random() * quotes.length)];

  // Add it to the page.
  const quoteContainer = document.getElementById('quote-container');
  quoteContainer.innerText = quote;
}

// Fetch greetings from server and add a random one to the page.
async function getGreeting() {

  fetch('/data').then(response => response.json()).then((greetings) => {
    const greetingElem = document.getElementById('greeting-container');
    var index = Math.floor(Math.random() * greetings.length);
    greetingElem.innerHTML = greetings[index];
  });
}

// Fetch comments from server and add a random one to page.
async function getComments() {

  fetch('/view-comments').then(response => response.json()).then((comments) => {
    const commentElem = document.getElementById('comment-container');
    commentElem.innerText = "";
    for (const comment of comments){
      commentElem.innerText += comment.user_comment + 
      " (" + Math.round(comment.sentiment * 10)/10 + ") \n";
    }
  });
}