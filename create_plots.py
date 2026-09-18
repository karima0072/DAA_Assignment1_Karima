import pandas as pd
import matplotlib.pyplot as plt
import numpy as np
import os
data = pd.read_csv("results.csv")
if not os.path.exists("plots"):
    os.mkdir("plots")
plt.figure(figsize=(8,5))
for algorithm in data["algorithm"].unique():
    for input_type in data["input"].unique():
        temp = data[(data["algorithm"] == algorithm) & (data["input"] == input_type)]
        plt.plot(temp["n"], temp["time_ms"], marker="o", label=algorithm + " " + input_type)

plt.xlabel("n")
plt.ylabel("Time (ms)")
plt.title("Time vs n")
plt.legend()
plt.grid()
plt.savefig("plots/time_vs_n.png")
plt.close()
plt.figure(figsize=(8,5))
for algorithm in data["algorithm"].unique():
    for input_type in data["input"].unique():
        temp = data[(data["algorithm"] == algorithm) & (data["input"] == input_type)]
        plt.plot(temp["n"], temp["max_depth"], marker="o", label=algorithm + " " + input_type)

plt.xlabel("n")
plt.ylabel("Max recursion depth")
plt.title("Recursion Depth vs n")
plt.legend()
plt.grid()
plt.savefig("plots/depth_vs_n.png")
plt.close()

plt.figure(figsize=(8,5))
for algorithm in data["algorithm"].unique():
    for input_type in data["input"].unique():
        temp = data[(data["algorithm"] == algorithm) & (data["input"] == input_type)]
        if algorithm == "QuickSelect":
            ratio = temp["comparisons"] / temp["n"]
        else:
            ratio = temp["comparisons"] / (temp["n"] * np.log2(temp["n"]))
        plt.plot(temp["n"], ratio, marker="o", label=algorithm + " " + input_type)
plt.xlabel("n")
plt.ylabel("Ratio")
plt.title("Theta Check")
plt.legend()
plt.grid()
plt.savefig("plots/ratio_vs_n.png")
plt.close()
print("Plots created")