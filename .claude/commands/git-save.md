# /git-save — Git 存档并推送（项目级）

将当前工作目录的改动用 git 提交并推送到远程仓库。

## 执行步骤

1. **检查状态**
   - 运行 `git status` 查看当前改动
   - 运行 `git log --oneline -3` 了解最近的提交历史

2. **处理未跟踪文件**
   - 如果有未跟踪文件（untracked），列出这些文件并询问用户：
     - 是否全部加入本次提交？
     - 还是只提交已修改的文件（modified/staged）？
   - 根据用户选择执行 `git add`

3. **生成 commit message**
   - 如果用户有明确的 commit message 意图，使用用户提供的
   - 否则基于 `git diff --stat` 和文件变更内容自动生成一条简洁、符合语义的 commit message（中文优先）
   - 生成后向用户确认，或让用户修改

4. **执行提交**
   - `git commit -m "<message>"`
   - 如果失败（如 hook 未通过），报告错误并停止

5. **推送到远程**
   - 运行 `git push`
   - 如果当前分支没有上游分支（upstream），自动执行 `git push -u origin <当前分支名>`
   - 推送成功后显示远程分支的最新状态

## 注意事项

- 如果没有改动需要提交，直接告知用户并退出
- 提交前不要执行 `git add -A`，必须尊重用户对未跟踪文件的选择
- 遵守用户已有的 commit message 风格（通过 `git log` 推断）
- 推送失败时（如网络、权限冲突），给出具体错误和解决建议