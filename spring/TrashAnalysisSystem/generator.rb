require 'date'

d1 = Date.parse("2015/01/01")
d2 = Date.parse("2018/05/27")

# 容積・重量の変換率
cw_table = {1 => 0.23, 2 => 0.41, 3 => 1.22, 4 => 0.42, 5 => 0.054, 6 => 1.13}
# cw_table = {1: 0.2, 2: 0.4, 3: 0, 4: 0, 5: 0, 6:0}

rand_u = ->{[1, 2].sample}

rand_t = ->{[1, 1, 1, 1, 1, 1, 2, 3, 3, 4, 5, 5].sample}
# 1: 燃える
# 2: 燃えない
# 3: 紙・布
# 4: びん・かん
# 5: ペットボトル
# 6: その他

rand_d = ->{rand(d1 .. d2)}

rand_c = ->{[5.0, 10.0, 20.0].sample}

rand_w = ->{rand(0.1, 5.0)}

1500.times do
    u = rand_u[]
    t = 1
    d = rand_d[]
    c = rand_c[]
    w = (cw_table[1] * c).round(3)
    puts "('#{u}', '#{t}', CAST('#{d}' as DATE), '#{c}', '#{w}'),"
end
