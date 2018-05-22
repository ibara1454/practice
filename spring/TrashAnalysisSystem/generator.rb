require 'date'

d1 = Date.parse("2017/01/01")
d2 = Date.parse("2018/05/27")

# 容積・重量の変換率
cw_table = {1 => 0.2, 2 => 0.4, 3 => 1.0, 4 => 0.4, 5 => 0.05, 6 => 1.0}
# cw_table = {1: 0.2, 2: 0.4, 3: 0, 4: 0, 5: 0, 6:0}

rand_u = ->{1}

rand_t = ->{[1, 1, 1, 1, 1, 1, 2, 3, 3, 4, 5, 5].sample}
# 1: 燃える
# 2: 燃えない
# 3: 紙・布
# 4: びん・かん
# 5: ペットボトル
# 6: その他

rand_d = ->{rand(d1 .. d2)}

rand_c = ->{[5, 10, 20].sample}

rand_w = ->{rand(0.1, 5.0)}

puts "INSERT INTO (user_id, trash_category_id, date, capacity, weight) VALUE "
500.times do
    u = rand_u[]
    t = 1
    d = rand_d[]
    c = rand_c[]
    w = cw_table[1] * c
    puts "('#{u}', '#{t}', '#{d}', '#{c}', '#{w}'),"
end
