#2025 ML
import numpy as np
import pygame
from time import time, sleep
from random import randint
import random

# parameters of maze
frame_interval = 0.1 # control run speed
n = 4 # size of maze
penalities = 4 # no. of red blocks

# parameters of q learning
alpha = 0.5 # learning rate
gamma = 0.9 # discount factor
epsilon = 1.0
epsilon_delta = 0.0001

# init maze
current_pos = (0,0)
reward = np.zeros((n,n))
terminals = [] 

background = (51,51,51)
screen = pygame.display.set_mode(((2*n+1)*100,n*100))
colors = [[background for i in range(n)] for j in range(n)]

while penalities > 0:
    i = randint(0,n-1)
    j = randint(0,n-1)
    if reward[i][j]==0 and (i,j)!=(0,0) and (i,j)!=(n-1,n-1):
        reward[i][j] = -1
        penalities -= 1
        colors[i][j] = (255,0,0)
        terminals.append((i,j))
        
reward[n-1][n-1] = 1
colors[n-1][n-1] = (0,255,0)
terminals.append((n-1,n-1))

# init q learning
Q = {}
for i in range(n):
    for j in range(n):
        Q[(i,j)] = [0] * 4

def select_action(pos):
    if np.random.random() <= epsilon:
        action = randint(0, 3)
    else:
        action = np.argmax(Q[tuple(pos)])
    return action
      
def step():
    
    global current_pos, epsilon_delta, epsilon
    #此处需要进行动作选取,给action赋值
    action = select_action(current_pos)

    new_pos = list(current_pos)
    if action==0 and new_pos[0]>0: #move up
        new_pos[0] -= 1
    elif action==1 and new_pos[0]<n-1: #move down
        new_pos[0] += 1
    elif action==2 and new_pos[1]>0: #move left
        new_pos[1] -= 1
    elif action==3 and new_pos[1]<n-1: #move right
        new_pos[1] += 1
    new_pos = tuple(new_pos)

    current_reward = reward[current_pos]

    if new_pos not in terminals:
        #此处实现Q-learning算法
        # 未到达最终状态，则采用贝尔曼方程更新Q函数
        Q[current_pos][action] += alpha * (
            current_reward + gamma * max(Q[new_pos]) - Q[current_pos][action]
        )
    else:
        # 此处实现Q-learning算法
        # 到达最终状态，则直接用当前奖励更新Q函数
        Q[current_pos][action] += alpha * (current_reward - Q[current_pos][action])
        
    if epsilon > 0.05:
        epsilon -= epsilon_delta
    current_pos = new_pos

def layout():
    for i in range(n):
        for j in range(n):
            pygame.draw.rect(screen, (255,255,255), (j*100,i*100,100,100), 0)
            pygame.draw.rect(screen, colors[i][j], (j*100+3,i*100+3,94,94), 0)
            pygame.draw.rect(screen, (255,255,255), ((j+n+1)*100,i*100,100,100), 0)
            vij = max(Q[(i,j)])
            qc = (0,int(255*vij),0) if vij>0 else (int(-255*vij),0,0)
            pygame.draw.rect(screen, qc, ((j+n+1)*100+3,i*100+3,94,94), 0)
    pygame.draw.circle(screen, (25,129,230), (current_pos[1]*100+50,current_pos[0]*100+50), 30, 0)
      
sleep_time = frame_interval
running = True
while running:
    step()
    sleep(sleep_time)
    screen.fill(background)
    layout()
    for event in pygame.event.get():
        if event.type == pygame.QUIT:
            running = False
        if event.type == pygame.KEYDOWN and event.key == pygame.K_SPACE:
            sleep_time = 0 if sleep_time>0 else frame_interval
    pygame.display.flip()
pygame.quit()