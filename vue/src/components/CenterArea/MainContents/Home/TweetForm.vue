<script setup>
import { ref } from 'vue'
import { tweets } from '@/consts/tweets.js'

const newTweetContent = ref('')

function addTweet() {
  if (newTweetContent.value.trim() !== '') {
    const currentDate = new Date()
    const year = currentDate.getFullYear()
    const month = String(currentDate.getMonth() + 1).padStart(2, '0') // 0から始まるため、1を足す
    const day = String(currentDate.getDate()).padStart(2, '0')
    const hours = String(currentDate.getHours()).padStart(2, '0')
    const minutes = String(currentDate.getMinutes()).padStart(2, '0')
    const seconds = String(currentDate.getSeconds()).padStart(2, '0')
    const formattedCurrentDate =
      year + '-' + month + '-' + day + ' ' + hours + ':' + minutes + ':' + seconds
    tweets.value.unshift({
      content: newTweetContent.value,
      userId: 'q30387',
      datetime: formattedCurrentDate,
      location: 'somewhere',
      likes: 0,
      retweet: 0,
      reply: 0,
      views: 0
    })
    newTweetContent.value = ''
  }
}
</script>
<template>
  <div>
    <div class="tweet-form">
      <img class="user-icon" src="@/assets/icons/user/myicon.svg" width="50" height="50" />
      <BButton pill variant="primary" :disabled="newTweetContent === ''" @click="addTweet">
        ツイート
      </BButton>
      <b-form-textarea
        id="new-tweet"
        v-model="newTweetContent"
        placeholder="いまどうしてる？"
        rows="3"
        max-rows="6"
        :maxlength="280"
        style="width: 600px"
      />
    </div>
  </div>
</template>
<style scoped>
.tweet-form {
  margin-bottom: 10px;
}
</style>
