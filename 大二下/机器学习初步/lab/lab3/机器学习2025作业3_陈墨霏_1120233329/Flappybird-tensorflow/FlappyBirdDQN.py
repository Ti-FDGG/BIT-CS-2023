#2025 ML
import tensorflow as tf
import cv2
import sys
sys.path.append("game/")
import game.wrapped_flappy_bird as game


import numpy as np
import csv






# preprocess raw image to 80*80 gray image
def preprocess(observation):
	observation = cv2.cvtColor(cv2.resize(observation, (80, 80)), cv2.COLOR_BGR2GRAY)
	ret, observation = cv2.threshold(observation,1,255,cv2.THRESH_BINARY)
	return np.reshape(observation,(80,80,1))

# 修改：添加参数，以便切换是否使用Target Network的模式
def playFlappyBird(useTargetNetwork=False):
	if useTargetNetwork:
		from BrainDQN_NIPS_addTargetNetwork import BrainDQN
		print("Using Target Network")
		out = open('record_addTargetNetwork.csv', 'a', newline='')
	else:
		from BrainDQN_NIPS import BrainDQN
		print("Not Using Target Network")
		out = open('record.csv', 'a', newline='')
	csv_write = csv.writer(out, dialect='excel')


	# Step 1: init BrainDQN
	actions = 2
	reward_sum = 0
	episode = 0
	reward_store = []


	brain = BrainDQN(actions)


	# Step 2: init Flappy Bird Game
	flappyBird = game.GameState()
	# Step 3: play game
	# Step 3.1: obtain init state
	action0 = np.array([1,0])  # do nothing
	observation0, reward0, terminal = flappyBird.frame_step(action0)

	observation0 = cv2.cvtColor(cv2.resize(observation0, (80, 80)), cv2.COLOR_BGR2GRAY)
	ret, observation0 = cv2.threshold(observation0,1,255,cv2.THRESH_BINARY)
	brain.setInitState(observation0)
	time1 = brain.timeStep
	# Step 3.2: run the game
	while 1!= 0:
		action = brain.getAction()
		nextObservation,reward,terminal = flappyBird.frame_step(action)

		nextObservation = preprocess(nextObservation)
		brain.setPerception(nextObservation,action,reward,terminal)
		if terminal:
			episode += 1
			r_sum = reward_sum
			time = brain.timeStep - time1
			reward_episode = [episode, time, r_sum]
			# 修改：添加了对于timeStep和epsilon的输出，以便观察训练情况
			print("episode=",episode,'reward=', r_sum, "timeStep=", brain.timeStep, "epsilon=", brain.epsilon)
			reward_store.append(r_sum)
			csv_write.writerow(reward_episode)

			reward_sum = 0
			time1 = brain.timeStep
		else:
			reward_sum += reward


# 修改：添加参数，以便切换是否使用Target Network的模式
def main(useTargetNetwork="False"):
	if useTargetNetwork == "True":
		playFlappyBird(useTargetNetwork=True)
	elif useTargetNetwork == "False":
		playFlappyBird(useTargetNetwork=False)

if __name__ == '__main__':
	# 运行时添加参数，True表示使用Target Network，False表示不使用
	if len(sys.argv) > 1:
		useTargetNetwork = sys.argv[1]
	else:
		useTargetNetwork = "False"
	main(useTargetNetwork)