# python运行相应的脚本前运行

source activate base

export LD_LIBRARY_PATH=$CONDA_PREFIX/lib:$LD_LIBRARY_PATH # 将 Conda 的库路径添加到 LD_LIBRARY_PATH 环境变量中：
export SDL_AUDIODRIVER=disk # 虚拟声卡